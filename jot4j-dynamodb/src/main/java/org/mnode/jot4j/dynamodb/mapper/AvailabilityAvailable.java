package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AvailabilityAvailable extends AbstractCalMapper {

    @DynamoDBAttribute(attributeName = "AvailabilityUid")
    private String availabilityUid;

    @DynamoDBAttribute(attributeName = "AvailableUid")
    private String availableUid;

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return "AVAILABILITY#" + availabilityUid;
    }

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return "AVAILABLE#" + availableUid;
    }

    @DynamoDBIndexHashKey(attributeName = "GSI1_PK", globalSecondaryIndexName = "GSI1")
    public String getGSI1PK() {
        return getSK();
    }

    @DynamoDBIndexRangeKey(attributeName = "GSI1_SK", globalSecondaryIndexName = "GSI1")
    public String getGSI1SK() {
        return getPK();
    }

    @Override
    @DynamoDBAttribute(attributeName = "TYPE")
    public String getType() {
        return "AVAILABILITY_AVAILABLE";
    }
}