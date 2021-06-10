package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Calendar extends AbstractCalMapper {

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBTypeConverted(converter = CalendarConverter.class)
    private net.fortuna.ical4j.model.Calendar data;

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return "CALENDAR#" + uid;
    }

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return getPK();
    }

    @Override
    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return "CALENDAR";
    }

    @DynamoDBIndexHashKey(attributeName = "GSI2_PK", globalSecondaryIndexName = "GSI2")
    public String getGSI2PK() {
        return getPK();
    }

    @DynamoDBIndexRangeKey(attributeName = "GSI2_SK", globalSecondaryIndexName = "GSI2")
    public String getGSI2SK() {
        return getSK();
    }
}
