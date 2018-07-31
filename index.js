'use strict';

const RN =  require('react-native');

module.exports = RN.Platform.OS === 'ios' ? RN.PickerIOS : require('./WheelPicker')
