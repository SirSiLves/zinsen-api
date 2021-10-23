package me.ruosch.zinsen.shared;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.util.ProxyUtils;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable
{
    private static final long serialVersionUID = -5554308939380869754L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Nullable
    protected Long oid;

    @Version
    @Column(name = "entityVersion")
    private int entityVersion;

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }
        BaseEntity that = (BaseEntity) obj;
        return null != this.getOid() && this.getOid().equals(that.getOid());
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getOid() ? 0 : getOid().hashCode() * 31;
        return hashCode;
    }


}
