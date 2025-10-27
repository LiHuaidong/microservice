package hdli;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description:
 *
 * @author: hendrix.li
 * @date: 2025-10-20 23:38
 */
public class WorkItemEvent {
    private static final long serialVersionUID = 1L;
    // 事件唯一标识
    private final String eventId;
    // 事件发生时间
    private final Date timestamp;
    // 关联的工作项ID
    private final String workItemId;
    // 工作项类型
    private final String workItemType;
    // 事件类型
    private final EventType eventType;
    // 事件参数
    private final Map<String, Object> parameters;
    // 监听器列表
    private final List<WorkItemListener> listeners = new CopyOnWriteArrayList<>();
    // 事件状态
    private EventStatus status = EventStatus.PENDING;

    // 构造方法
    public WorkItemEvent(String eventId, EventType eventType, String workItemId, String workItemType) {
        this.eventId = eventId;
        this.timestamp = new Date();
        this.eventType = eventType;
        this.workItemId = workItemId;
        this.workItemType = workItemType;
        this.parameters = new HashMap<>();
    }

    // 序列化工具方法
    public static WorkItemEvent fromJson(String json) {
        // 实现JSON反序列化逻辑
        return null;
    }

    // 添加监听器
    public void addListener(WorkItemListener listener) {
        listeners.add(listener);
    }

    // 移除监听器
    public void removeListener(WorkItemListener listener) {
        listeners.remove(listener);
    }

    // 触发事件
    public void triggerEvent() {
        setStatus(EventStatus.PROCESSING);
        notifyListeners();
        setStatus(EventStatus.COMPLETED);
    }

    // 事件通知
    private void notifyListeners() {
        for (WorkItemListener listener : listeners) {
            try {
                listener.onWorkItemEvent(this);
            } catch (Exception e) {
                handleListenerException(e);
            }
        }
    }

    // 异常处理
    private void handleListenerException(Exception e) {
        // 实现日志记录或重试机制
        System.err.println("事件监听异常: " + e.getMessage());
    }

    // Getters
    public String getEventId() {
        return eventId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getWorkItemId() {
        return workItemId;
    }

    public String getWorkItemType() {
        return workItemType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Map<String, Object> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public EventStatus getStatus() {
        return status;
    }

    // 状态设置
    public synchronized void setStatus(EventStatus status) {
        this.status = status;
    }

    public String toJson() {
        // 实现JSON序列化逻辑
        return "";
    }

    // 事件类型枚举
    public enum EventType {
        WORKITEM_CREATED,
        WORKITEM_UPDATED,
        WORKITEM_COMPLETED,
        WORKITEM_ABORTED,
        WORKITEM_ASSIGNED
    }

    // 事件状态枚举
    public enum EventStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED
    }

    // 监听器接口
    public interface WorkItemListener {
        void onWorkItemEvent(WorkItemEvent event);
    }
}
