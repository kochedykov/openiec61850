/**
 * This class file was automatically generated by jASN1 v1.9.1-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.openiec61850.internal.mms.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;

public class TypeSpecification implements Serializable {

    private static final long serialVersionUID = 1L;

    public byte[] code = null;
    private TypeDescription typeDescription = null;

    public TypeSpecification() {
    }

    public TypeSpecification(byte[] code) {
        this.code = code;
    }

    public void setTypeDescription(TypeDescription typeDescription) {
        this.typeDescription = typeDescription;
    }

    public TypeDescription getTypeDescription() {
        return typeDescription;
    }

    public int encode(OutputStream os) throws IOException {

        if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
                os.write(code[i]);
            }
            return code.length;
        }

        int codeLength = 0;
        if (typeDescription != null) {
            codeLength += typeDescription.encode(os);
            return codeLength;
        }

        throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
    }

    public int decode(InputStream is) throws IOException {
        return decode(is, null);
    }

    public int decode(InputStream is, BerTag berTag) throws IOException {

        int codeLength = 0;
        BerTag passedTag = berTag;

        if (berTag == null) {
            berTag = new BerTag();
            codeLength += berTag.decode(is);
        }

        typeDescription = new TypeDescription();
        int choiceDecodeLength = typeDescription.decode(is, berTag);
        if (choiceDecodeLength != 0) {
            return codeLength + choiceDecodeLength;
        }
        else {
            typeDescription = null;
        }

        if (passedTag != null) {
            return 0;
        }

        throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
        ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
        encode(os);
        code = os.getArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

        if (typeDescription != null) {
            sb.append("typeDescription: ");
            typeDescription.appendAsString(sb, indentLevel + 1);
            return;
        }

        sb.append("<none>");
    }

}
