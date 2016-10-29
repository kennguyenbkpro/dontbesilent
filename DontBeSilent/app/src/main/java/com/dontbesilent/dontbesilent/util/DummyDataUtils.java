package com.dontbesilent.dontbesilent.util;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import com.dontbesilent.dontbesilent.data.CampaignActivity;
import com.dontbesilent.dontbesilent.data.CampaignComment;
import com.dontbesilent.dontbesilent.data.Donation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by CuTi on 10/30/2016.
 */

public class DummyDataUtils {
    public static ArrayList<Donation> mDonations = new ArrayList<>();
    public static ArrayList<CampaignActivity> mActivities = new ArrayList<>();
    public static ArrayList<CampaignComment> mComments = new ArrayList<>();

    private static Random random = new Random();
    private static String[] donators = new String[] {
            "Ken Nguyễn", "QuangBX", "Ly Nguyen", "LuatNT", "LamHX",
            "Nguyễn Văn A", "Bé Châu", "Dì Tám", "Trần Văn Bê", "Huỳnh Thị Long Lanh",
            "Công ty ABC", "Người giấu tên", "Hồng", "Mike", "Vi Nguyễn",
            "Quốc Nam", "Công ty XYZ", "Anh Tài"
    };

    private static int[] donationType = new int[] {
            Donation.TYPE_BOOK, Donation.TYPE_CLOTHES, Donation.TYPE_MEDICINE,
            Donation.TYPE_MONEY, Donation.TYPE_OTHER
    };

    private static String DONATION_MESSAGE_MONEY = " ủng hộ %d đồng";
    private static String DONATION_MESSAGE_BOOK = " ủng hộ %d bộ sách giáo khoa";
    private static String DONATION_MESSAGE_CLOTHES = " ủng hộ %d bộ quần áo";
    private static String DONATION_MESSAGE_MEDICINE = " ủng hộ %d hộp cứu thương";
    private static String DONATION_MESSAGE_OTHER = " ủng hộ %d thùng mỳ tôm";

    public static ArrayList<Donation> getDummyDonation() {
        int numDonation = 1 + random.nextInt(20);
        ArrayList<Donation> donations = new ArrayList<>();
        for (int i = 0 ; i < numDonation; i++) {
            Donation donation = new Donation();
            donation.type = donationType[random.nextInt(donationType.length)];
            donation.donator = donators[random.nextInt(donators.length)];
            genDummyDonationMessage(donation);
            donations.add(donation);
        }
        mDonations = donations;
        return donations;
    }

    public static void genDummyDonationMessage(Donation donation) {
        String donationMessageTemplate = "";
        switch (donation.type) {
            case Donation.TYPE_BOOK:
                donationMessageTemplate = String.format(DONATION_MESSAGE_BOOK, 5 + random.nextInt(20));
                break;
            case Donation.TYPE_CLOTHES:
                donationMessageTemplate = String.format(DONATION_MESSAGE_CLOTHES, 5 + random.nextInt(30));
                break;
            case Donation.TYPE_MEDICINE:
                donationMessageTemplate = String.format(DONATION_MESSAGE_MEDICINE, 2 + random.nextInt(10));
                break;
            case Donation.TYPE_MONEY:
                donationMessageTemplate = String.format(DONATION_MESSAGE_MONEY, 100000 + random.nextInt(50) * 100000);
                break;
            case Donation.TYPE_OTHER:
                donationMessageTemplate = String.format(DONATION_MESSAGE_OTHER, 5 + random.nextInt(100));
                break;
        }
        donationMessageTemplate = donation.donator + donationMessageTemplate;
        Spannable wordtoSpan = new SpannableString(donationMessageTemplate);
        wordtoSpan.setSpan(new StyleSpan(Typeface.BOLD), 0, donation.donator.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        donation.description = wordtoSpan;
    }

    public static ArrayList<CampaignComment> getDummyComments() {
        String[] comments = new String[] {
                "Donec imperdiet vestibulum dolor sit amet ultricies.",
                "Curabitur tristique euismod ipsum sit amet elementum. Integer sit amet lacus fringilla, vehicula metus vitae, aliquet leo.",
                "Fusce facilisis dui vitae tortor malesuada, eget interdum orci viverra. Morbi bibendum elementum odio, nec bibendum odio. ",
                "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus elementum fringilla augue facilisis pellentesque.",
                "Sed mi ex, cursus vitae bibendum a, sagittis et turpis. Cras lectus orci, vestibulum ac nulla et, consequat venenatis ipsum.",
                "Curabitur varius gravida accumsan. Aenean enim leo, ornare non ligula et, mollis lobortis nisl. Suspendisse mattis consequat diam non dictum.",
                "Fusce gravida congue lacus vel imperdiet. Aenean vel augue id nulla dignissim vestibulum.",
                "Duis id iaculis orci.",
                "Fusce mi ipsum, eleifend a rhoncus ut, tempus non augue. Sed non sem non sem sollicitudin condimentum.",
                "Morbi et ante sodales, tempor mi commodo, laoreet sem.",
                "Integer varius libero vel ex consequat imperdiet. Sed tempus sodales nulla, eget placerat quam volutpat non.",
                "Integer elit justo, ullamcorper dictum hendrerit non, porttitor vitae risus.",
                "Praesent elementum, velit et euismod tincidunt, lectus tortor tincidunt leo, eget hendrerit lacus diam et enim.",
                "Phasellus elementum sapien eu orci dignissim, et commodo risus convallis.",
                "Donec sodales nisi id odio pellentesque fermentum."
        };
        int numComment = 5 + random.nextInt(20);
        ArrayList<CampaignComment> campaignComments = new ArrayList<>();
        for (int i = 0 ; i < numComment; i++) {
            CampaignComment campaignComment = new CampaignComment();
            campaignComment.username = donators[random.nextInt(donators.length)];
            campaignComment.comment = comments[random.nextInt(comments.length)];
            campaignComments.add(campaignComment);
        }
        mComments = campaignComments;
        return campaignComments;
    }

    public static ArrayList<CampaignActivity> getDummyActivities() {
        String[] activities = new String[] {
                "Curabitur sollicitudin, libero ac fringilla ultrices, risus erat venenatis dolor, et sagittis felis augue ut augue.",
                "Proin id viverra leo, et suscipit orci. Pellentesque elit lectus, congue at mauris nec, elementum fermentum est.",
                "Suspendisse ut quam nec odio scelerisque efficitur vitae quis nunc.",
                "Suspendisse sed dignissim eros, nec imperdiet quam.",
                "Vivamus consequat, felis sit amet euismod tristique, massa urna tristique ipsum, vel eleifend leo lorem vitae orci.",
                "Suspendisse dui augue, consequat eget velit vitae, malesuada semper orci.",
                "Fusce diam diam, bibendum vel vulputate at, placerat consectetur dui.",
                "In consectetur mauris id purus dapibus, ut accumsan neque rhoncus.",
                "Vivamus sit amet porttitor tellus.",
                "Mauris ultricies mauris sed gravida ultrices. Donec lectus leo, eleifend sit amet iaculis vitae, dignissim vel odio.",
                "Suspendisse potenti. Phasellus vitae lacus velit.",
                "Cras eu leo molestie, aliquam leo vel, viverra magna.",
                "Curabitur condimentum porta tincidunt.",
                "Nulla elit sapien, rutrum sed venenatis et, consequat ut felis.",
                "Donec eleifend ex sed placerat rhoncus. Nulla facilisi. Morbi purus velit, eleifend non enim id, ornare laoreet nunc. Sed eu aliquam tellus, ut maximus nulla."
        };
        int numActivity = 5 + random.nextInt(20);
        ArrayList<CampaignActivity> campaignActivities = new ArrayList<>();
        for (int i = 0 ; i < numActivity; i++) {
            CampaignActivity campaignActivity = new CampaignActivity();
            campaignActivity.username = donators[random.nextInt(donators.length)];
            campaignActivity.content = activities[random.nextInt(activities.length)];
            campaignActivities.add(campaignActivity);
        }
        mActivities = campaignActivities;
        return campaignActivities;
    }
}
