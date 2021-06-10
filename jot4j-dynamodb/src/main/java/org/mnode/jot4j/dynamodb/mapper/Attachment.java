package org.mnode.jot4j.dynamodb.mapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.nio.ByteBuffer;

@AllArgsConstructor
@Builder
@Data
public class Attachment extends AbstractCalMapper {

    private final String pkPrefix;

    @DynamoDBAttribute(attributeName = "Uid")
    private String uid;

    @DynamoDBAttribute(attributeName = "MessageDigest")
    private String messageDigest;

    @DynamoDBAttribute(attributeName = "Binary")
    private ByteBuffer binary;

    public Attachment(String pkPrefix) {
        this.pkPrefix = pkPrefix;
    }

    @Override
    @DynamoDBHashKey(attributeName = "PK")
    public String getPK() {
        return pkPrefix + "#" + uid;
    }

    @Override
    @DynamoDBRangeKey(attributeName = "SK")
    public String getSK() {
        return "ATTACH#" + messageDigest;
    }

    @Override
    public String getType() {
        return pkPrefix + "_ATTACH";
    }
}
