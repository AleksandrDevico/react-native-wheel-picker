package com.wheelpicker;

import android.support.annotation.Nullable;

import android.graphics.Color;
import android.graphics.Typeface;
import com.aigestudio.wheelpicker.WheelPicker;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.text.ReactFontManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WheelPickerManager extends SimpleViewManager<WheelPicker> implements WheelPicker.OnItemSelectedListener{

    public static final String REACT_CLASS = "WheelPicker";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected WheelPicker createViewInstance(ThemedReactContext context) {
        WheelPicker wheelPicker = new WheelPicker(context);
        wheelPicker.setOnItemSelectedListener(this);
        return wheelPicker;
    }

    @ReactProp(name = "data")
    public void setData(WheelPicker wheelPicker, ReadableArray data) {
        if (wheelPicker != null) {
            List<String> emptyList = new ArrayList<>();
            try {
                List<String> labelData = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    ReadableMap itemMap = data.getMap(i);
                    labelData.add(itemMap.getString("label"));
                }
                if (labelData.size() != 0) {
                    wheelPicker.setData(labelData);
                }
            } catch (Exception ex){
                ex.printStackTrace();
                wheelPicker.setData(emptyList);
            }
        }
    }

    @ReactProp(name = "isCurved")
    public void setCurved(WheelPicker wheelPicker, Boolean isCurved) {
        if (wheelPicker !=null){
            wheelPicker.setCurved(isCurved);
        }
    }

    @ReactProp(name = "isCyclic")
    public void setCyclic(WheelPicker wheelPicker, Boolean isCyclic) {
        if (wheelPicker != null){
            wheelPicker.setCyclic(isCyclic);
        }
    }

    @ReactProp(name = "isAtmospheric")
    public void setAtmospheric(WheelPicker wheelPicker, Boolean isAtmospheric) {
        if (wheelPicker != null){
            wheelPicker.setAtmospheric(isAtmospheric);
        }
    }

    @ReactProp(name = "selectedItemTextColor")
    public void setSelectedItemTextColor(WheelPicker wheelPicker, String selectedItemTextColor) {
        if (wheelPicker != null){
            wheelPicker.setSelectedItemTextColor(Color.parseColor(normalizeColor(selectedItemTextColor)));
        }
    }

    @ReactProp(name = "itemSpace")
    public void setItemSpace(WheelPicker wheelPicker, int itemSpace) {
        if (wheelPicker != null){
            wheelPicker.setItemSpace((int) PixelUtil.toPixelFromDIP(itemSpace));
        }
    }

    @ReactProp(name = "visibleItemCount")
    public void setVisibleItemCount(WheelPicker wheelPicker, int visibleItemCount) {
        if (wheelPicker != null){
            wheelPicker.setVisibleItemCount(visibleItemCount);
        }
    }

    @ReactProp(name = "renderIndicator")
    public void setIndicator(WheelPicker wheelPicker, Boolean renderIndicator) {
        if (wheelPicker != null){
            wheelPicker.setIndicator(renderIndicator);
        }
    }

    @ReactProp(name = "indicatorColor")
    public void setIndicatorColor(WheelPicker wheelPicker, String indicatorColor) {
        if (wheelPicker != null){
            wheelPicker.setIndicatorColor(Color.parseColor(normalizeColor(indicatorColor)));
        }
    }

    @ReactProp(name = "indicatorSize")
    public void setIndicatorColor(WheelPicker wheelPicker, int indicatorSize) {
        if (wheelPicker != null){
            wheelPicker.setIndicatorSize(indicatorSize);
        }
    }

    @ReactProp(name = "isCurtain")
    public void setCurtain(WheelPicker wheelPicker, Boolean isCurtain) {
        if (wheelPicker != null){
            wheelPicker.setCurtain(isCurtain);
        }
    }

    @ReactProp(name = "curtainColor")
    public void setCurtainColor(WheelPicker wheelPicker, String curtainColor) {
        if (wheelPicker != null){
            wheelPicker.setCurtainColor(Color.parseColor(normalizeColor(curtainColor)));
        }
    }

    @ReactProp(name = "itemTextColor")
    public void setItemTextColor(WheelPicker wheelPicker, String itemTextColor) {
        if (wheelPicker != null){
            wheelPicker.setItemTextColor(Color.parseColor(normalizeColor(itemTextColor)));
        }
    }

    @ReactProp(name = "itemTextSize")
    public void setItemTextSize(WheelPicker wheelPicker, int itemTextSize) {
        if (wheelPicker != null){
            wheelPicker.setItemTextSize((int) PixelUtil.toPixelFromDIP(itemTextSize));
        }
    }

    @ReactProp(name = "itemTextFontFamily")
    public void setItemTextFontFamily(WheelPicker wheelPicker, String itemTextFontFamily) {
      if (wheelPicker != null){
        Typeface typeface = ReactFontManager.getInstance().getTypeface(
            itemTextFontFamily,
            Typeface.NORMAL,
            wheelPicker.getContext().getAssets()
        );
        wheelPicker.setTypeface(typeface);
      }
    }

    @ReactProp(name = "selectedItemPosition")
    public void setSelectedItemPosition(WheelPicker wheelPicker, int selectedItemPosition) {
        if (wheelPicker != null){
            wheelPicker.setSelectedItemPosition(selectedItemPosition);
        }
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(WheelPicker wheelPicker, String backgroundColor) {
        if (wheelPicker != null){
            wheelPicker.setBackgroundColor(Color.parseColor(normalizeColor(backgroundColor)));
        }
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        WritableMap event = Arguments.createMap();
        try {
            event.putString("data", (String) data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((ReactContext)picker.getContext())
            .getJSModule(RCTEventEmitter.class)
            .receiveEvent(
                picker.getId(),
                "itemSelected",
                event
            );
    }

    @Override
    public @Nullable Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder
            .of(
                "itemSelected",
                MapBuilder.of("registrationName", "onItemSelected")
            );
    }

    private String normalizeColor(String color) {
        if (color.length() == 4 && color.charAt(0) == '#') {
            String normalized = "#"
                    + color.charAt(1) + color.charAt(1)
                    + color.charAt(2) + color.charAt(2)
                    + color.charAt(3) + color.charAt(3);
            return normalized;
        }
        return color;
    }
}
