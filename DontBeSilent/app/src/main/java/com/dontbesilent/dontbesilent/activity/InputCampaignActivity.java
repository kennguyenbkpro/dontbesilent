package com.dontbesilent.dontbesilent.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.dontbesilent.dontbesilent.R;
import com.dontbesilent.dontbesilent.data.Campaign;
import com.dontbesilent.dontbesilent.data.Event;
import com.dontbesilent.dontbesilent.data.Host;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputCampaignActivity extends AppCompatActivity {

    private EditText name, imgUrl, address, startTime, endTime;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_campaign);
        name = (EditText) findViewById(R.id.name);
        imgUrl = (EditText) findViewById(R.id.imgUrl);
        address = (EditText) findViewById(R.id.address);
        startTime = (EditText) findViewById(R.id.startTime);
        endTime = (EditText) findViewById(R.id.endTime);
        name = (EditText) findViewById(R.id.name);
        name = (EditText) findViewById(R.id.name);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("campaigns").limitToLast(5).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String data = dataSnapshot.getKey();
                dataSnapshot.getValue();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                writeNewCampaign("-KVGT2x8pWNsoyybxlCI", "5 năm ròng, cậu bé sáng bò tới lớp", "-KVG9kmf7e5XBO-HAYvJ", "-KVGK3tNSTfGKVxej3CQ", "Lầu A Sáng chưa bao giờ được đi một đôi dép mới, bởi suốt từ nhỏ tới lớn, em đều đến trường bằng đôi tay và đầu gối của mình.", "27/10/2016", "5/11/2016", 15000000.0, 5000000.0, 213L);
//                writeNewCampaign("-KVGT2xb_ZBHvQahIwOd", "Đắng lòng bà mẹ chờ chết phải lo 3 con thơ nheo nhóc", "-KVG9kmxguSYtzV8BLgO", "-KVGK3t5CaXlNr0n78rg", "Ôm chặt đứa con còn đỏ hỏn vào lòng, chị chỉ biết cúi gằm xuống để cố giấu đi gương mặt biến dạng của mình mà khóc. Căn bệnh ung thư đã vào giai đoạn cuối khiến chị chỉ còn sống bằng giây, bằng phút nhưng 3 đứa con thơ dại không biết sẽ ra sao?", "28/10/2016", "10/11/2016", 25000000.0, 10000000.0, 314L);
//                writeNewCampaign("-KVGT2y0kXRwub9_lxn1", "Làm ơn chung tay giúp cho anh Điệp – cặp vợ chồng bệnh tật nghèo khó", "-KVG9knBDBqhbBcnBFSK", "-KVGK3so3UTq-hRyF0S1", "Hộ khẩu ở Kim Giao Nam nhưng mấy năm nay, gia đình anh Phạm Văn Điệp phải xuống Diêu Quang (cùng xã Hoài Hải, huyện Hoài Nhơn, tỉnh Bình Định), cách đó hơn 4km ăn nhờ ở đậu. Đặc biệt, tai họa ập đến hồi cuối năm 2013, khiến anh Điệp mất nguyên cả phần chân phải sau vụ tai nạn giao thông khiến cuộc sống gia đình càng trở nên khốn đốn.", "28/10/2016", "10/11/2016", 17000000.0, 11000000.0, 125L);
//                writeNewCampaign("-KVGT2yQFCT2ZyuWGQFT", "Phát động ủng hộ đồng bào Bắc miền Trung bị lũ lụt", "-KVG9km7EGeirW-Aelgd", "-KVGK3sSyBPnW_yJansN", "Nhằm chia sẻ khó khăn, mất mát với đồng bào các tỉnh Bắc miền Trung đang gánh chịu thiệt hại nặng nề do mưa lũ gây ra trong những ngày vừa qua, sáng nay 18/10, Tỉnh ủy - HĐND - UBND - Ủy ban MTTQ Việt Nam tỉnh Quảng Nam tổ chức lễ phát động ủng hộ đồng bào bị thiệt hại lũ lụt tại các tỉnh Bắc miền Trung.", "30/10/2016", "20/11/2016", 120000000.0, 75000000.0, 513L);
//                writeNewCampaign("-KVGT2yq42pStRiQ39Jj", "Hội chữ thập đỏ tổ chức Lễ phát động ủng hộ đồng bào các tỉnh miền Trung", "-KVG9knBDBqhbBcnBFSK", "-KVGK3sSyBPnW_yJansN", "Không chỉ buổi phát động hôm nay, tôi rất mong thời gian tới bằng các chương trình hành động cụ thể, như Đoàn Thanh niên cộng sản Hồ Chí Minh của Bộ NNPTNT đã có chương trình vào Quảng Bình để tặng các suất quà cũng như chia sẻ với đồng bào ở Quảng Bình", "30/10/2016", "15/11/2016", 75000000.0, 7000000.0, 336L);

//                writeNewEvent("-KVGK3sSyBPnW_yJansN", "Ủng hộ miền trung lũ lụt", "Hương Trạch, Hương Khê, Hà Tĩnh", 30000000.0, 13000000.0);
//                writeNewEvent("-KVGK3so3UTq-hRyF0S1", "Ủng hộ cặp vợ chồng bệnh tật nghèo khó", "Xã Hoài Hải, huyện Hoài Nhơn, tỉnh Bình Định", 70000000.0, 23000000.0);
//                writeNewEvent("-KVGK3t5CaXlNr0n78rg", "Ủng hộ bà mẹ chờ chết phải lo 3 con thơ nheo nhóc", "Thôn Đồng Nhân, xã Hòa Tiến, huyện Yên Phong, tỉnh Bắc Ninh", 50000000.0, 27000000.0);
//                writeNewEvent("-KVGK3tNSTfGKVxej3CQ", "Ủng hộ cậu bé 5 năm ròng bò đến lớp", "Thị trấn Nông trường Cờ Đỏ (Mộc Châu, Sơn La)", 20000000.0, 10000000.0);

//                writeNewHost("-KVG9km7EGeirW-Aelgd", "muahexanh@gmail.com", "Mùa hè xanh", true, "36 Đường Trần Khắc Chân, Phường Tân Định, Quận 1, Hồ Chí Minh");
//                writeNewHost("-KVG9kmNfBKctQY-2KLZ", "thanhnien@gmail.com", "Thanh niên", true, "D31 Đường Vườn Lan, Phường Phường 10, Tân Bình, Hồ Chí Minh");
//                writeNewHost("-KVG9kmf7e5XBO-HAYvJ", "tamlongvang@gmail.com", "Tấm lòng vàng", true, "56 Đường Phó Cơ Điều, Phường 12, Quận 5, Hồ Chí Minh");
//                writeNewHost("-KVG9kmxguSYtzV8BLgO", "nhahaotam@gmail.com", "Nhà hảo tâm", true, "283 Đường Trường Chinh, Phường 14, Tân Bình, Tp.Hồ Chí Minh");
//                writeNewHost("-KVG9knBDBqhbBcnBFSK", "hoichuthapdo@gmail.com", "Hội chữ thập đỏ", true, "127B Đường Văn Cao, Phường Phú Thọ Hòa, Tân Phú, Tp.Hồ Chí Minh");

//                mDatabase.child("campaigns").limitToLast(10).addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                        if(dataSnapshot.getKey().equals("-KVGT2yq42pStRiQ39Jj")) {
//                            Campaign campaign = dataSnapshot.getValue(Campaign.class);
//                            campaign.image = "https://hieuminh.files.wordpress.com/2013/11/lc5a9-mie1bb81n-trung.jpg";
//                            mDatabase.child("campaigns").child("-KVGT2yq42pStRiQ39Jj").setValue(campaign);
//                        } else if(dataSnapshot.getKey().equals("-KVGT2yQFCT2ZyuWGQFT")) {
//                            Campaign campaign = dataSnapshot.getValue(Campaign.class);
//                            campaign.image = "http://cms.kienthuc.net.vn/uploaded/quocquan/2015_09_17/hom-nay-ngap-lut-de-doa-cac-tinh-mien-trung.jpg";
//                            mDatabase.child("campaigns").child("-KVGT2yQFCT2ZyuWGQFT").setValue(campaign);
//                        } else if(dataSnapshot.getKey().equals("-KVGT2y0kXRwub9_lxn1")) {
//                            Campaign campaign = dataSnapshot.getValue(Campaign.class);
//                            campaign.image = "http://files.tamsugiadinh.vn/News/1970/01/con-bi-gian-nao-benh-tim-ngang.jpg";
//                            mDatabase.child("campaigns").child("-KVGT2y0kXRwub9_lxn1").setValue(campaign);
//                        } else if(dataSnapshot.getKey().equals("-KVGT2xb_ZBHvQahIwOd")) {
//                            Campaign campaign = dataSnapshot.getValue(Campaign.class);
//                            campaign.image = "http://www.nhantu.com/images/post/2014/11/08/12//DSC_1421-709ed.JPG";
//                            mDatabase.child("campaigns").child("-KVGT2xb_ZBHvQahIwOd").setValue(campaign);
//                        } else if(dataSnapshot.getKey().equals("-KVGT2x8pWNsoyybxlCI")) {
//                            Campaign campaign = dataSnapshot.getValue(Campaign.class);
//                            campaign.image = "http://ngoctrongtim.org/wp-content/uploads/2015/12/4a41f9a6-ba1d-4366-801e-be8a8d5b0008-580x321.jpg";
//                            mDatabase.child("campaigns").child("-KVGT2x8pWNsoyybxlCI").setValue(campaign);
//                        }
//                    }
//
//                    @Override
//                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

//                mDatabase.child("hosts").limitToLast(10).addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                        if(dataSnapshot.getKey().equals("-KVG9km7EGeirW-Aelgd")) {
//                            Host host = dataSnapshot.getValue(Host.class);
//                            host.avatar = "http://dongphucviet.com/upload/images/Logo-mua-he-xanh.jpg";
//                            host.cover = "https://i.ytimg.com/vi/uTYyR-VXPEM/hqdefault.jpg";
//                            mDatabase.child("hosts").child("-KVG9km7EGeirW-Aelgd").setValue(host);
//                        } else if(dataSnapshot.getKey().equals("-KVG9kmNfBKctQY-2KLZ")) {
//                            Host host = dataSnapshot.getValue(Host.class);
//                            host.avatar = "http://www.nguoiviettre.org.vn/uploads/News/pic/small_nvn_1363749099.jpg";
//                            host.cover = "http://tinhdoannghean.vn/images/news/anh%20dep%20tinh%20nguyen.jpg";
//                            mDatabase.child("hosts").child("-KVG9kmNfBKctQY-2KLZ").setValue(host);
//                        } else if(dataSnapshot.getKey().equals("-KVG9kmf7e5XBO-HAYvJ")) {
//                            Host host = dataSnapshot.getValue(Host.class);
//                            host.avatar = "https://yt3.ggpht.com/-3pYgFpNdwlM/AAAAAAAAAAI/AAAAAAAAAAA/EuabJ8BDkms/s88-c-k-no-mo-rj-c0xffffff/photo.jpg";
//                            host.cover = "http://fodacon.com.vn/uploads/albums/images/2014_05/7.jpg";
//                            mDatabase.child("hosts").child("-KVG9kmf7e5XBO-HAYvJ").setValue(host);
//                        } else if(dataSnapshot.getKey().equals("-KVG9kmxguSYtzV8BLgO")) {
//                            Host host = dataSnapshot.getValue(Host.class);
//                            host.avatar = "http://nhanaiclub.com/uploads/news/thumb/logonho_38.jpg";
//                            host.cover = "http://www.benhviennhi.org.vn//upload/files/Thien%20Phuoc/tu%20thien.Jpeg";
//                            mDatabase.child("hosts").child("-KVG9kmxguSYtzV8BLgO").setValue(host);
//                        } else if(dataSnapshot.getKey().equals("-KVG9knBDBqhbBcnBFSK")) {
//                            Host host = dataSnapshot.getValue(Host.class);
//                            host.avatar = "http://chuthapdotphcm.org.vn/fckeditor/editor/filemanager/connectors/aspx/userfiles/image/HIEN%20MAU/image001.jpg";
//                            host.cover = "http://hoichuthapdohanoi.vn/uploads/news/2016_09/logo-dai-hoi.jpg";
//                            mDatabase.child("hosts").child("-KVG9knBDBqhbBcnBFSK").setValue(host);
//                        }
//                    }
//
//                    @Override
//                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });


//                    if(dataSnapshot.getKey().equals("-KVGK3sSyBPnW_yJansN")) {
//                        Event event = dataSnapshot.getValue(Event.class);
//                        event.image = "https://hieuminh.files.wordpress.com/2013/11/lc5a9-mie1bb81n-trung.jpg";
//                        mDatabase.child("events").child("-KVGK3sSyBPnW_yJansN").setValue(event);
//                    } else if(dataSnapshot.getKey().equals("-KVGK3so3UTq-hRyF0S1")) {
//                        Event event = dataSnapshot.getValue(Event.class);
//                        event.image = "http://files.tamsugiadinh.vn/News/1970/01/con-bi-gian-nao-benh-tim-ngang.jpg";
//                        mDatabase.child("events").child("-KVGK3so3UTq-hRyF0S1").setValue(event);
//                    } else if(dataSnapshot.getKey().equals("-KVGK3t5CaXlNr0n78rg")) {
//                        Event event = dataSnapshot.getValue(Event.class);
//                        event.image = "http://www.nhantu.com/images/post/2014/11/08/12//DSC_1421-709ed.JPG";
//                        mDatabase.child("events").child("-KVGK3t5CaXlNr0n78rg").setValue(event);
//                    } else if(dataSnapshot.getKey().equals("-KVGK3tNSTfGKVxej3CQ")) {
//                        Event event = dataSnapshot.getValue(Event.class);
//                        event.image = "http://ngoctrongtim.org/wp-content/uploads/2015/12/4a41f9a6-ba1d-4366-801e-be8a8d5b0008-580x321.jpg";
//                        mDatabase.child("events").child("-KVGK3tNSTfGKVxej3CQ").setValue(event);
//                    }

            }
        });
    }


    private void writeNewHost(String user, String name, Boolean isVerified, String desception) {
        Host host = new Host(user, name, isVerified, desception);
        mDatabase.child("hosts").push().setValue(host);
    }

    private void writeNewEvent(String name, String address, Double goalMoney, Double incomeMoney) {
        Event event = new Event(name, address, goalMoney, incomeMoney);
        mDatabase.child("evens").push().setValue(event);
    }

    private void writeNewCampaign(String name, String hostId, String eventId, String desception, String startTime, String endTime, Double goalMoney, Double incomeMoney, Long numFollower) {
        Campaign campaign = new Campaign(name, hostId, eventId, desception, startTime, endTime, goalMoney, incomeMoney, numFollower);
        mDatabase.child("campaigns").push().setValue(campaign);
    }

}
