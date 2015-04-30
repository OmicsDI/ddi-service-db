package uk.ac.ebi.ddi.service.db.model.logger;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 30/04/2015
 */
@Document(collection = "logger.Event" )
public class Event extends AbstractDocument implements Serializable{

    String rawMessage;

    String logName;

    @DBRef
    Resource resource;

    public Event(){}

    public Event(String rawMessage, String logName) {
        this.rawMessage = rawMessage;
        this.logName    = logName;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (logName != null ? !logName.equals(event.logName) : event.logName != null) return false;
        if (rawMessage != null ? !rawMessage.equals(event.rawMessage) : event.rawMessage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rawMessage != null ? rawMessage.hashCode() : 0;
        result = 31 * result + (logName != null ? logName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "rawMessage='" + rawMessage + '\'' +
                ", logName='" + logName + '\'' +
                '}';
    }
}
