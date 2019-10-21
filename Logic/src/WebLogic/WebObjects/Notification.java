package WebLogic.WebObjects;

public class Notification {
    private String m_text;
    private String m_CreatedTime;

    public Notification(String m_text, String m_CreatedTime) {
        this.m_text = m_text;
        this.m_CreatedTime = m_CreatedTime;
    }

    public Notification(String notification) {
        String[] noti = notification.split("~");
        this.m_text = noti[0];
        this.m_CreatedTime = noti[1];
        System.out.println(this.m_text);
        System.out.println(this.m_CreatedTime);
    }

    public String getM_text() {
        return m_text;
    }

    public String getM_CreatedTime() {
        return m_CreatedTime;
    }

    public void setM_text(String m_text) {
        this.m_text = m_text;
    }

    public void setM_CreatedTime(String m_CreatedTime) {
        this.m_CreatedTime = m_CreatedTime;
    }
}
