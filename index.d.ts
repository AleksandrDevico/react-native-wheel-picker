declare module "react-native-wheel-picker" {
  import * as React from "react"
  import { StyleProp, NativeSyntheticEvent, TextStyle, ViewStyle } from "react-native"

  export interface WheelPickerProps {
    itemStyle?: StyleProp<TextStyle>,
    onValueChange?: (arg: string | number) => void,
    selectedValue?: string | number,
    style?: StyleProp<ViewStyle>,
  }

  export type State = {
    selectedItemPosition: number
  }

  export interface WheelPickerItemProps {
    label: string,
    value: string | number,
  }

  export class WheelPickerItem extends React.Component<WheelPickerItemProps> {}

  export default class WheelPicker extends React.Component<WheelPickerProps, State> {
    static Item: typeof WheelPickerItem
  }

}
