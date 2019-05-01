package com.diabin.latte.ec.icon;

import com.joanzapata.iconify.Icon;

public enum EcIcons implements Icon {
    icon_debug('\ue6da');

    private char charator;

    EcIcons(char charator) {
        this.charator = charator;
    }

    @Override
    public String key() {
        return this.name().replace('_', '-');
    }

    @Override
    public char character() {
        return charator;
    }
}