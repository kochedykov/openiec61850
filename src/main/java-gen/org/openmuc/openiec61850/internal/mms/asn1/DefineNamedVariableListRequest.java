/**
 * This class file was automatically generated by jASN1 v1.9.1-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.openiec61850.internal.mms.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;

public class DefineNamedVariableListRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

    public byte[] code = null;
    private ObjectName variableListName = null;
    private VariableDefs listOfVariable = null;

    public DefineNamedVariableListRequest() {
    }

    public DefineNamedVariableListRequest(byte[] code) {
        this.code = code;
    }

    public void setVariableListName(ObjectName variableListName) {
        this.variableListName = variableListName;
    }

    public ObjectName getVariableListName() {
        return variableListName;
    }

    public void setListOfVariable(VariableDefs listOfVariable) {
        this.listOfVariable = listOfVariable;
    }

    public VariableDefs getListOfVariable() {
        return listOfVariable;
    }

    public int encode(OutputStream os) throws IOException {
        return encode(os, true);
    }

    public int encode(OutputStream os, boolean withTag) throws IOException {

        if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
                os.write(code[i]);
            }
            if (withTag) {
                return tag.encode(os) + code.length;
            }
            return code.length;
        }

        int codeLength = 0;
        codeLength += listOfVariable.encode(os, false);
        // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
        os.write(0xA0);
        codeLength += 1;

        codeLength += variableListName.encode(os);

        codeLength += BerLength.encodeLength(os, codeLength);

        if (withTag) {
            codeLength += tag.encode(os);
        }

        return codeLength;

    }

    public int decode(InputStream is) throws IOException {
        return decode(is, true);
    }

    public int decode(InputStream is, boolean withTag) throws IOException {
        int codeLength = 0;
        int subCodeLength = 0;
        BerTag berTag = new BerTag();

        if (withTag) {
            codeLength += tag.decodeAndCheck(is);
        }

        BerLength length = new BerLength();
        codeLength += length.decode(is);

        int totalLength = length.val;
        codeLength += totalLength;

        subCodeLength += berTag.decode(is);
        variableListName = new ObjectName();
        subCodeLength += variableListName.decode(is, berTag);
        subCodeLength += berTag.decode(is);

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
            listOfVariable = new VariableDefs();
            subCodeLength += listOfVariable.decode(is, false);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
        }
        throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: "
                + subCodeLength);

    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
        ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
        encode(os, false);
        code = os.getArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

        sb.append("{");
        sb.append("\n");
        for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
        }
        if (variableListName != null) {
            sb.append("variableListName: ");
            variableListName.appendAsString(sb, indentLevel + 1);
        }
        else {
            sb.append("variableListName: <empty-required-field>");
        }

        sb.append(",\n");
        for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
        }
        if (listOfVariable != null) {
            sb.append("listOfVariable: ");
            listOfVariable.appendAsString(sb, indentLevel + 1);
        }
        else {
            sb.append("listOfVariable: <empty-required-field>");
        }

        sb.append("\n");
        for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
    }

}
