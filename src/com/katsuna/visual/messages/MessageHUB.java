/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.katsuna.visual.messages;

import java.util.ArrayList;
import java.util.List;

public class MessageHUB {

    public static final int MEDIA_BUTTON_CLICKED = 0;

    public static final int MEASUREMENT_STEP = 1;

    public static final int DONE_CALIBRATION = 2;

    public static final int DONE_MEASUREMENT = 3;

    private static MessageHUB _instance = new MessageHUB();

    public static MessageHUB get() {
        return _instance;
    }

    private final List<MessageListener> _listenerList = new ArrayList<MessageListener>();

    public boolean registerListener(final MessageListener listener) {
        if (!_listenerList.contains(listener)) {
            return _listenerList.add(listener);
        }

        return false;
    }

    public boolean unregisterListener(final MessageListener listener) {
        return _listenerList.remove(listener);
    }

    public void sendMessage(final int messageID, final Object message) {
        for (MessageListener ml : _listenerList) {
            ml.onMessage(messageID, message);
        }
    }

}
