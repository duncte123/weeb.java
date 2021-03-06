/*
 *    Copyright 2018 - 2019 Duncan Sterken
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

package me.duncte123.weebJava.models.settings;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.natanbc.reliqua.request.PendingRequest;
import me.duncte123.weebJava.models.settings.responses.SettingsResponse;
import me.duncte123.weebJava.models.settings.responses.SubSettingsListResponse;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("unused")
public interface SettingsManager {

    /**
     * @param type
     *         The type of setting that you want to get
     * @param id
     *         The id for the type
     *
     * @return The {@link SettingsResponse} holding the settings data from the server
     */
    @Nonnull
    PendingRequest<SettingsResponse> getSetting(@Nonnull String type, @Nonnull String id);

    /**
     * @param type
     *         The type of setting that you want to get
     * @param id
     *         The id for the type
     * @param data
     *         The json data of your settings to store, if the setting for this type and id does not exits it will be
     *         created
     *         <p><strong>This overwrites your previous data</strong>
     *         <p>So make sure to include changed and not-changed fields so you don't overwrite anything precious!
     *
     * @return The {@link SettingsResponse} holding the settings data from the server
     */
    @Nonnull
    PendingRequest<SettingsResponse> updateSetting(@Nonnull String type, @Nonnull String id, @Nonnull JsonNode data);

    /**
     * Deletes a setting from the server <strong>THIS CAN NOT BE UNDONE.</strong>
     *
     * @param type
     *         The type of setting to delete
     * @param id
     *         the id of the setting to delete
     *
     * @return The {@link SettingsResponse} holding the settings data from the server
     */
    @Nonnull
    PendingRequest<SettingsResponse> deleteSetting(@Nonnull String type, @Nonnull String id);


    /**
     * @param type
     *         The parent type to list the sub-settings for
     * @param id
     *         The parent id to list the sub-settings for
     * @param subtype
     *         The sub-type to list the sub-settings for
     *
     * @return The {@link SubSettingsListResponse} with a list of the sub-settings
     * <p>
     * Note: <strong>You don't have to create a parent setting to be able to use the sub-settings of it</strong>
     */
    @Nonnull
    PendingRequest<SubSettingsListResponse> listSubSettings(@Nonnull String type, @Nonnull String id, @Nonnull String subtype);

    /**
     * @param type
     *         The parent type of setting that you want to get
     * @param id
     *         The parent id for the type
     * @param subtype
     *         The sub-type of setting that you are trying to get
     * @param subId
     *         The sub-id of setting that you are trying to get
     *
     * @return The {@link SettingsResponse} holding the settings data from the server
     */
    @Nonnull
    PendingRequest<SettingsResponse> getSubSetting(@Nullable String type, @Nullable String id, @Nonnull String subtype, @Nonnull String subId);

    /**
     * @param type
     *         The parent type of setting that you want to get
     * @param id
     *         The parent id for the type
     * @param subtype
     *         The sub-type of setting that you are trying to get
     * @param subId
     *         The sub-id of setting that you are trying to get
     * @param data
     *         The json data of your settings to store, if the setting for this type and id does not exits it will be
     *         created
     *         <p><strong>This overwrites your previous data</strong>
     *         <p>So make sure to include changed and not-changed fields so you don't overwrite anything precious!
     *
     * @return The {@link SettingsResponse} holding the settings data from the server
     */
    @Nonnull
    PendingRequest<SettingsResponse> updateSubSetting(@Nullable String type, @Nullable String id, @Nonnull String subtype, @Nonnull String subId, @Nonnull JsonNode data);

    /**
     * Deletes a setting from the server <strong>THIS CAN NOT BE UNDONE.</strong>
     *
     * @param type
     *         The parent type of setting to delete
     * @param id
     *         the parent id of the setting to delete
     * @param subtype
     *         The sub-type of the setting that you want to delete
     * @param subId
     *         The sub-id of the setting that you want to delete
     *
     * @return The {@link SettingsResponse} holding the settings data from the server
     */
    @Nonnull
    PendingRequest<SettingsResponse> deleteSubSetting(@Nullable String type, @Nullable String id, @Nonnull String subtype, @Nonnull String subId);
}
