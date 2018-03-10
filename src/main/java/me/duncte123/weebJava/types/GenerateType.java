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

package me.duncte123.weebJava.types;

public enum GenerateType {

    /**
     * This is the awooo type: <br>
     * <img src="https://camo.githubusercontent.com/feccaf7383796470a6e3928fbe0ea70ae6fc78bf/68747470733a2f2f692e696d6775722e636f6d2f39734a32317a332e706e67" alt="awooo">
     */
    AWOOO("awooo"),
    /**
     * This is the eyes type: <br>
     * <img src="https://camo.githubusercontent.com/653bf35a8ca176348f809f98722b6b13dc44e379/68747470733a2f2f692e696d6775722e636f6d2f7139554c33664e2e706e67" alt="eyes">
     */
    EYES("eyes"),
    /**
     * This is the won type: <br>
     * <img src="https://camo.githubusercontent.com/9efe397262c080edd233023b43b890081357433a/68747470733a2f2f692e696d6775722e636f6d2f686461704973422e706e673f31" alt="won">
     */
    WON("won");

    private final String type;

    GenerateType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    /**
     * Returns the type as a string
     *
     * @return the type as a string
     */
    public String getType() {
        return type;
    }
}
