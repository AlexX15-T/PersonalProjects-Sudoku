import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class MyDocumentFilter extends DocumentFilter {

    int max = 1;

    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        int documentLength = fb.getDocument().getLength();
        if (documentLength - length + text.length() <= max && Character.isDigit(text.charAt(0)) && text.charAt(0) != '0' )
            super.replace(fb, offset, length, text.toUpperCase(), attrs);
    }

}


