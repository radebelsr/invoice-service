package za.co.bmw.invoice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public abstract class AbstractAuditable extends AbstractPersistable<Long> implements Auditable<String, Long> {
    private static final int ROUNDING = 2;
    @JsonIgnore
    @CreatedBy
    @Column
    private String createdBy;

    @JsonIgnore
    @CreatedDate
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(columnDefinition = "datetime")
    private DateTime createdDate = DateTime.now();

    @JsonIgnore
    @LastModifiedBy
    @Column
    private String lastModifiedBy;

    @JsonIgnore
    @LastModifiedDate
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(columnDefinition = "datetime")
    private DateTime lastModifiedDate = DateTime.now();

    public BigDecimal round(BigDecimal value){
        return value.setScale(ROUNDING, RoundingMode.HALF_UP);
    }
}