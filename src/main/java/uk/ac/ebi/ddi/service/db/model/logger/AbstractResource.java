package uk.ac.ebi.ddi.service.db.model.logger;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Enumeration;

/**
 * A Generic resourceUUID in the database, the resourceUUID can be use as the way of storing all the resources in:
 *  - ftp url
 *  - pride inspector
 *  - http://www.ebi.ac.uk/pride/Project/PX000001
 *
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 */

public abstract class AbstractResource extends AbstractDocument{

    @Indexed(unique = true)
    String resourceUUID;


    /**
     * Default constructor for the resourceUUID.
     */
    public AbstractResource(){}

    public AbstractResource(String resourceUUID) {
        this.resourceUUID = resourceUUID;
    }

    public String getResourceUUID() {
        return resourceUUID;
    }

    public void setResourceUUID(String resourceUUID) {
        this.resourceUUID = resourceUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractResource that = (AbstractResource) o;

        if (resourceUUID != null ? !resourceUUID.equals(that.resourceUUID) : that.resourceUUID != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return resourceUUID != null ? resourceUUID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResourceAccess{" +
                "resourceUUID='" + resourceUUID + '\'' +
                '}';
    }
}
