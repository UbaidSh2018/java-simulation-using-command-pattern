package org.oracle.site;


/**
 * Interface to all Squares on the construction site
 */
public interface Square {

    /**
     * Return an object of type {@link Square}
     * @param type char
     * @return instance of {@link Square}
     */
    static Square squareFor(int type) {
        switch(type) {
            case 'o': return new PlainSquare();
            case 't': return new TreeSquare();
            case 'r': return new RockySquare();
            case 'T': return new ProtectedSquare();
            default: throw new IllegalArgumentException("type "+(char)type);
        }
    }
}
