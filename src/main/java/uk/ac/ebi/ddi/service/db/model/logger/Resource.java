package uk.ac.ebi.ddi.service.db.model.logger;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 */

@Document(collection = "logger.Resource")

public class Resource extends AbstractDocument{

    @Indexed(unique = true)
    String resource;

    /**
     * Default constructor for the resource.
     */
    public Resource(){}

    public Resource(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource that = (Resource) o;

        if (resource != null ? !resource.equals(that.resource) : that.resource != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return resource != null ? resource.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ResourceAccess{" +
                "resource='" + resource + '\'' +
                '}';
    }
}
