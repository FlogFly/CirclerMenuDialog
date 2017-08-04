package com.chint.circlemenudialog;

import java.io.Serializable;
import java.util.List;

/**
 * Project:ChintDataH5
 * Author:dyping
 * Date:2017/6/30 9:55
 */

public class DialogMenuBean implements Serializable{


    private List<ItemBean> item;

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ItemBean {
        /**
         * name : 吃饭
         * iconName : icon_launch
         * typeId : 12
         */

        private String name;
        private String iconName;
        private String typeId;

        public ItemBean(String name, String iconName, String typeId) {
            this.name = name;
            this.iconName = iconName;
            this.typeId = typeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIconName() {
            return iconName;
        }

        public void setIconName(String iconName) {
            this.iconName = iconName;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }
    }
}
