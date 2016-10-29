package com.dontbesilent.dontbesilent.util;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import com.dontbesilent.dontbesilent.MainApplication;
import com.dontbesilent.dontbesilent.R;
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

    private static String[] avatarUrls = new String[] {
            "http://images.tapchianhdep.net/15-10bo-hinh-anh-avatar-dep-lang-man9.jpg",
            "http://i.9mobi.vn/cf/images/2015/04/nkk/hinh-avatar-dep-15.jpg",
            "http://i.9mobi.vn/cf/images/2015/04/nkk/hinh-avatar-dep-14.jpg",
            "http://4.bp.blogspot.com/-Q0tonCxQZ_Q/VibwOE-rq5I/AAAAAAAAAH4/7qolcObxD7I/s1600/hinh-avatar-dep-cho-facebook-1.jpg",
            "http://tapchianhdep.com/wp-content/uploads/2016/05/bo-suu-tap-hinh-anh-avatar-doc-chat-nhat-qua-dat-3-220x162.jpg",
            "http://vanvat.net/hinhanh/anhto/14326hinh-anh-avatar-doi-ban-khi-nho.jpg",
            "http://s1-media.123mua.vn/2014/7/c/7c5e18b52f3c678f63df1ecf978715c5_200x200.jpg",
            "http://thietbididong.net/wp-content/uploads/2015/08/avatar-2-9-hinh-anh-avatar-dep-ngay-2-9-1.png",
            "http://i.9mobi.vn/cf/images/2015/04/nkk/hinh-avatar-dep-12.jpg",
            "http://anhnendep.net/wp-content/uploads/2016/07/hinh-avatar-tinh-yeu-de-thuong-01.jpg",
            "http://file.vforum.vn/hinh/2013/6/hinh-avatar-dep-3.jpg",
            "http://thietbididong.net/wp-content/uploads/2015/08/avatar-2-9-hinh-anh-avatar-dep-ngay-2-9-2.jpg",
            "http://taochu.trangnhat.net/avatars/thumbs/animal276.gif.thumb.jpg",
            "http://thietbididong.net/wp-content/uploads/2015/04/tai-hinh-anh-avatar-la-co-viet-nam-quoc-ky-to-quoc-dep-nhat-2015-2.jpg",
            "http://vanvat.net/hinhanh/anhto/14731avatar-ngo-nginh=tre-tho-cau-be-dang-yeu.jpg",
            "http://3.bp.blogspot.com/-NLEYOcF2ydI/UOxZPEO3W2I/AAAAAAAAbCg/2muzCN0yM28/s1600/Avatar+kh%E1%BB%89+yoyo+cici+(11).jpg",
            "http://vanvat.net/hinhanh/anhto/14730avatar-hinh-ve-ong-mat-troi.jpg",
            "http://hinh-nen.org/images/131214avatar-dau-hoi-sanh-dieu-va-doc-dao.jpg",
            "http://hinhanhdepvip.com/wp-content/uploads/2016/06/hinh-avatar-buon.jpg",
            "http://chuotnhat84.xtgem.com/hinh-nen-rong-3d/hinh-nen-rong-3d-130.jpg",
            "http://k14.vcmedia.vn/Images/Uploaded/Share/2010/11/21/101121wall11.jpg",
            "http://gocchiase360.com/wp-content/uploads/2014/02/hinh-avatar-dep-nhat-ve-tinh-yeu-9.jpg",
            "http://bloganhdep.net/wp-content/uploads/2014/08/top-hinh-nen-ipad-duoc-quan-tam-nhat-01.jpg"
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
            donation.avatarUrl = avatarUrls[random.nextInt(avatarUrls.length)];
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
            campaignComment.avatarUrl = avatarUrls[random.nextInt(avatarUrls.length)];
            int userType = random.nextInt(3);
            if (userType == 0) {
                campaignComment.userType = MainApplication.getInstance().getResources().getString(R.string.user_type_donator);
            } else if (userType == 1) {
                campaignComment.userType = MainApplication.getInstance().getResources().getString(R.string.user_type_host);
            } else {
                campaignComment.userType = MainApplication.getInstance().getResources().getString(R.string.user_type_admin);
            }
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

        String[] urls = new String[] {
                "http://www.longnhanai.net/upload/IMG_3589.JPG",
                "http://125.235.29.7/media01/4ecda901441c7/2013/08/29/85003398-205f-4257-be6c-0341910aa788.jpg",
                "http://www.daophatngaynay.com/vn/files/images/2011/quy4/1_PIC_1891_708459819.jpg",
                "http://hungphatea.com/uploads/albums/tuithien/momat2.jpg",
                "http://www.hoituthienphohien.com/2011pics/vulanbinhdinh_01.jpg",
                "http://img.v3.news.zdn.vn/w660/Uploaded/Ycgmvlbp/2016_02_12/mailanvan1.jpg",
                "http://khamnamkhoa.net/wp-content/uploads/2015/01/tu-thien-cam-hoi-sapa-9.jpg",
                "http://images.sunflower.vn/wp-content/uploads/2016/01/du-lich-tu-thien-hinh-anh-03.jpg",
                "https://vietbao.com/images/file/atCBwZp20QgBAMIk/pghh-lam-tu-thien.jpg",
                "http://hungphatea.com/uploads/albums/tuithien/momat4.jpg",
                "http://hungphatea.com/uploads/albums/tuithien/momat1.jpg",
                "http://newsen.vn/data/news/2016/7/18/31/Ho-Ngoc-Ha-cung-hoc-tro-The-Face-di-tu-thien-5-1468833423.jpg",
                "http://img.tintuc.vietgiaitri.com/2015/5/20/duc-tien-ve-nuoc-lam-tu-thien-sau-2-nam-dinh-cu-my-07b8f3.jpg"
        };
        int numActivity = 5 + random.nextInt(20);
        ArrayList<CampaignActivity> campaignActivities = new ArrayList<>();
        for (int i = 0 ; i < numActivity; i++) {
            CampaignActivity campaignActivity = new CampaignActivity();
            campaignActivity.username = donators[random.nextInt(donators.length)];
            campaignActivity.content = activities[random.nextInt(activities.length)];
            campaignActivity.avatarUrl = avatarUrls[random.nextInt(avatarUrls.length)];
            boolean hasImage = (random.nextInt(2) == 0);
            if (hasImage) {
                campaignActivity.url = urls[random.nextInt(urls.length)];
            }
            campaignActivities.add(campaignActivity);
        }
        mActivities = campaignActivities;
        return campaignActivities;
    }
}
