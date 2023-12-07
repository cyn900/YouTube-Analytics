package views;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.video_stats.VideoStatsState;
import interface_adapter.video_stats.VideoStatsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VideoStatsView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "video stats";

    private final VideoStatsViewModel videoStatsViewModel;

    JLabel videoId;

    JLabel channelName;

    JLabel videoTitle;

    JLabel description;

    JLabel videoPublishDate;

    JLabel viewCount;

    JLabel likeCount;

    JLabel commentCount;

    private final JButton back;


    public VideoStatsView(VideoStatsViewModel videoStatsViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel) {

        this.videoStatsViewModel = videoStatsViewModel;
        this.videoStatsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Video Stats Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        JPanel panel7 = new JPanel();
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));

        videoId = new JLabel();
        videoId.setAlignmentX(Component.CENTER_ALIGNMENT);

        channelName = new JLabel();
        channelName.setAlignmentX(Component.CENTER_ALIGNMENT);

        videoTitle = new JLabel();
        videoTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        description = new JLabel();
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        videoPublishDate = new JLabel();
        videoPublishDate.setAlignmentX(Component.CENTER_ALIGNMENT);

        viewCount = new JLabel();
        viewCount.setAlignmentX(Component.CENTER_ALIGNMENT);

        likeCount = new JLabel();
        likeCount.setAlignmentX(Component.CENTER_ALIGNMENT);

        commentCount = new JLabel();
        commentCount.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(600, 600);

//        this.setLayout(new GridLayout(3, 1, 0, 5));

        panel3.add(title);
        panel5.add(videoId);
        panel5.add(channelName);
        panel5.add(videoTitle);
        panel5.add(description);
        panel5.add(videoPublishDate);
        panel5.add(viewCount);
        panel5.add(likeCount);
        panel5.add(commentCount);

        back = new JButton(VideoStatsViewModel.BACK_BUTTON_LABEL);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel7.add(back);

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            viewManagerModel.setActiveView(homeViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        panel1.add(panel3);
        panel1.add(panel5);
        panel1.add(panel7);

        this.add(panel1);

//        this.add(panel3);
//        this.add(panel5);
//        this.add(panel7);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        VideoStatsState state = (VideoStatsState) evt.getNewValue();
        videoId.setText("Video ID: " + state.getVideoId());
        channelName.setText("Channel name: " + state.getChannelName());
        videoTitle.setText("Video name: " + state.getTitle());
        description.setText("Description: " + state.getDescription());
        videoPublishDate.setText("Video publish date: " + state.getVideoPublishDate().toString());
        viewCount.setText("View count: " + state.getViewCount());
        likeCount.setText("Like count: " + state.getLikeCount());
        commentCount.setText("Comment count: " + state.getCommentCount());
    }
}
