/*
 *    Copyright 2018 Duncan Sterken
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package me.duncte123.weebJava.models.settings.responses;

import me.duncte123.weebJava.models.settings.objects.SettingsObject;
import me.duncte123.weebJava.models.settings.objects.SubSettingsObject;

@SuppressWarnings("unused")
public class SubSettingsResponse extends SettingsResponse {

    private final SubSettingsObject subsetting;

    public SubSettingsResponse(int status, String message, SettingsObject setting, SubSettingsObject subsetting) {
        super(status, message, setting);
        this.subsetting = subsetting;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubSettingsObject getSubsetting() {
        return subsetting;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SettingsObject getSetting() {
        throw new IllegalArgumentException("Cannot get settings on sub-settings");
    }
}
