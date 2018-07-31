import React from 'react';
import { View, Text, requireNativeComponent } from 'react-native';

const WheelPickerNative = requireNativeComponent('WheelPicker', WheelPicker);

type Props = {
  onValueChange?: (arg: string | number) => void,
  selectedValue?: string | number,
  style?: View.props.style,
  itemStyle?: Text.props.style
}

type State = { selectedItemPosition: number }

type ItemProps = {
  label: string,
  value: string | number,
}

class WheelPickerItem extends React.Component<ItemProps> {
  render() {
    return null
  }
}

export default class WheelPicker extends React.Component<Props, State> {

  state = {
    selectedItemPosition: 0,
  }

  subscription = undefined

  static Item = WheelPickerItem

  constructor(props) {
    super(props)
    this.state = this._stateFromProps(props)
  }

  componentWillReceiveProps(nextProps: Props) {
    this.setState(this._stateFromProps(nextProps))
  }

  _stateFromProps = (props) => {
    let selectedIndex = 0
    const data = []
    const {
      children,
      itemStyle = {},
      onValueChange,
      selectedValue,
      style = {},
    } = props

    React.Children.forEach(children, (child, i) => {
      if (child.props.value === selectedValue) {
        selectedIndex = i
      }
      data.push({ value: child.props.value, label: child.props.label })
    })

    const { backgroundColor, ...styles } = style
    const { color, fontFamily, fontSize = 24 } = itemStyle

    return {
      curtainColor: undefined,
      data,
      indicatorColor: color,
      indicatorSize: 3,
      isAtmospheric: true,
      isCurtain: false,
      isCurved: true,
      isCyclic: false,
      itemSpace: fontSize * 1.5,
      itemTextColor: color,
      itemTextFontFamily: fontFamily,
      itemTextSize: fontSize,
      renderIndicator: true,
      selectedItemPosition: selectedIndex,
      selectedItemTextColor: undefined,
      style: { height: 80, width: 100, ...styles },
      visibleItemCount: undefined,
    }
  }

  onItemSelected = (e) => {
    if (this.props.onValueChange) {
      this.props.onValueChange(e.nativeEvent.data)
    }
  }

  render() {
    return (
      <WheelPickerNative {...this.state} onItemSelected={this.onItemSelected} />
    )
  }
}
