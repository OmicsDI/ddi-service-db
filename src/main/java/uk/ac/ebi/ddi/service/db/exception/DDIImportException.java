package uk.ac.ebi.ddi.service.db.exception;

/**
 * @author Rui Wang, Yasset Perez-Riverol
 * @version $Id$
 */
public class DDIImportException extends RuntimeException {
    public DDIImportException() {
    }

    public DDIImportException(String message) {
        super(message);
    }

    public DDIImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public DDIImportException(Throwable cause) {
        super(cause);
    }

    public DDIImportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
