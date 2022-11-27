import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;

public class StartGUI extends JPanel {
    public StartGUI() {
        JLabel titleLabel = new JLabel("Welcome to the slang word dictionary", JLabel.CENTER);
        JLabel idLabel = new JLabel("MSSV: 20127135");
        JLabel nameLabel = new JLabel("Họ và tên: Trần Huỳnh Ngọc Diệp");
        JLabel subjectLabel = new JLabel("Môn học: CSC13102 - Lập trình ứng dụng Java");
        JLabel classLabel = new JLabel("Lớp: 20KTPM2");
        JLabel projectLabel = new JLabel("Đồ án môn học: ĐỒ ÁN #1 – SLANG WORD - DAMH#1");
        JLabel guideLabel = new JLabel("Click the MENU in the left top of the frame to open the menu", JLabel.CENTER);

        titleLabel.setFont(App.HEADING_FONT);
        idLabel.setFont(App.NORMAL_FONT);
        nameLabel.setFont(App.NORMAL_FONT);
        subjectLabel.setFont(App.NORMAL_FONT);
        classLabel.setFont(App.NORMAL_FONT);
        projectLabel.setFont(App.NORMAL_FONT);
        guideLabel.setFont(App.NORMAL_FONT);

        JPanel contentLabel = new JPanel();
        contentLabel.setLayout(new BoxLayout(contentLabel, BoxLayout.Y_AXIS));
        contentLabel.add(Box.createVerticalGlue());
        contentLabel.add(idLabel);
        contentLabel.add(nameLabel);
        contentLabel.add(subjectLabel);
        contentLabel.add(classLabel);
        contentLabel.add(projectLabel);
        contentLabel.add(Box.createVerticalGlue());

        titleLabel.setPreferredSize(new Dimension(0, 50));
        guideLabel.setPreferredSize(new Dimension(0, 50));

        // Align left (LINE_START)
        JPanel leftAlign = new JPanel();
        leftAlign.setPreferredSize(new Dimension(50, 0));

        // Card 0
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.PAGE_START);
        add(leftAlign, BorderLayout.LINE_START);
        add(contentLabel, BorderLayout.CENTER);
        add(guideLabel, BorderLayout.PAGE_END);
    }
}