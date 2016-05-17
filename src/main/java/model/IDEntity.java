package model;

/**
 * Created by Valeriy Holovko on 12.05.2016.
 */
public abstract class IDEntity {
    //this class is implemented in order to obtain a unique id for each object
    private static int lastID;
    private int id;

    {
        lastID++;
    }

    protected IDEntity() {
        id = lastID;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IDEntity idEntity = (IDEntity) o;

        return id == idEntity.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
