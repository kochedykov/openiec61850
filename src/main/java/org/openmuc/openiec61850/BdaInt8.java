/*
 * Copyright 2011-17 Fraunhofer ISE, energy & meteo Systems GmbH and other contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.openmuc.openiec61850;

import org.openmuc.jasn1.ber.types.BerInteger;
import org.openmuc.openiec61850.internal.mms.asn1.Data;
import org.openmuc.openiec61850.internal.mms.asn1.TypeDescription;
import org.openmuc.openiec61850.internal.mms.asn1.Unsigned8;

public final class BdaInt8 extends BasicDataAttribute {

    private byte value;

    public BdaInt8(ObjectReference objectReference, Fc fc, String sAddr, boolean dchg, boolean dupd) {
        super(objectReference, fc, sAddr, dchg, dupd);
        basicType = BdaType.INT8;
        setDefault();
    }

    public void setValue(byte value) {
        this.value = value;
    }

    @Override
    public void setValueFrom(BasicDataAttribute bda) {
        value = ((BdaInt8) bda).getValue();
    }

    public byte getValue() {
        return value;
    }

    @Override
    public void setDefault() {
        value = 0;
    }

    @Override
    public BdaInt8 copy() {
        BdaInt8 copy = new BdaInt8(objectReference, fc, sAddr, dchg, dupd);
        copy.setValue(value);
        if (mirror == null) {
            copy.mirror = this;
        }
        else {
            copy.mirror = mirror;
        }
        return copy;
    }

    @Override
    Data getMmsDataObj() {
        Data data = new Data();
        data.setInteger(new BerInteger(value));
        return data;
    }

    @Override
    void setValueFromMmsDataObj(Data data) throws ServiceError {
        if (data.getInteger() == null) {
            throw new ServiceError(ServiceError.TYPE_CONFLICT, "expected type: integer");
        }
        value = data.getInteger().value.byteValue();
    }

    @Override
    TypeDescription getMmsTypeSpec() {
        TypeDescription typeDescription = new TypeDescription();
        typeDescription.setInteger(new Unsigned8(8));
        return typeDescription;
    }

    @Override
    public String toString() {
        return getReference().toString() + ": " + value;
    }

    @Override
    public String getValueString() {
        return "" + value;
    }

}
