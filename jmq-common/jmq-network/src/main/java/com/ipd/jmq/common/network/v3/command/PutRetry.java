package com.ipd.jmq.common.network.v3.command;

import com.ipd.jmq.common.message.BrokerMessage;
import com.ipd.jmq.toolkit.lang.Preconditions;

import java.util.Arrays;

/**
 * 创建重试数据
 */
public class PutRetry extends JMQPayload {
    // 主题
    private String topic;
    // 应用
    private String app;
    // 重试原因
    private String exception;
    // 消息
    private BrokerMessage[] messages;

    public PutRetry topic(final String topic) {
        setTopic(topic);
        return this;
    }

    public PutRetry app(final String app) {
        setApp(app);
        return this;
    }

    public PutRetry messages(final BrokerMessage... messages) {
        setMessages(messages);
        return this;
    }

    public PutRetry exception(String exception) {
        setException(exception);
        return this;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getApp() {
        return this.app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public BrokerMessage[] getMessages() {
        return this.messages;
    }

    public void setMessages(BrokerMessage[] messages) {
        this.messages = messages;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int predictionSize() {
        int size = Serializer.getPredictionSize(topic, app) + Serializer.getPredictionSize(exception, 2);
        size += Serializer.getPredictionSize(messages);
        size += 1;
        return size;
    }


    @Override
    public void validate() {
        super.validate();
        Preconditions.checkArgument(topic != null && !topic.isEmpty(), "topic can not be empty");
        Preconditions.checkArgument(app != null && !app.isEmpty(), "app can not be empty");
        Preconditions.checkArgument(messages != null && messages.length > 0, "message can not be empty");
    }

    @Override
    public int type() {
        return CmdTypes.PUT_RETRY;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PutRetry{");
        sb.append("topic='").append(topic).append('\'');
        sb.append(", app='").append(app).append('\'');
        sb.append(", exception='").append(exception).append('\'');
        sb.append(", messages=").append(Arrays.toString(messages));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        PutRetry putRetry = (PutRetry) o;

        if (app != null ? !app.equals(putRetry.app) : putRetry.app != null) {
            return false;
        }
        if (exception != null ? !exception.equals(putRetry.exception) : putRetry.exception != null) {
            return false;
        }
        if (!Arrays.equals(messages, putRetry.messages)) {
            return false;
        }
        if (topic != null ? !topic.equals(putRetry.topic) : putRetry.topic != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (app != null ? app.hashCode() : 0);
        result = 31 * result + (exception != null ? exception.hashCode() : 0);
        result = 31 * result + (messages != null ? Arrays.hashCode(messages) : 0);
        return result;
    }
}