package genius.mykhatamulquranbeta;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomSwipeAdpter extends PagerAdapter {


    private int[] image_resources = new int[]{

            R.drawable.a604, R.drawable.a603, R.drawable.a602, R.drawable.a601,

            R.drawable.a600, R.drawable.a599, R.drawable.a598, R.drawable.a597, R.drawable.a596, R.drawable.a595, R.drawable.a594, R.drawable.a593, R.drawable.a592, R.drawable.a591,
            R.drawable.a590, R.drawable.a589, R.drawable.a588, R.drawable.a587, R.drawable.a586, R.drawable.a585, R.drawable.a584, R.drawable.a583, R.drawable.a582, R.drawable.a581,
            R.drawable.a580, R.drawable.a579, R.drawable.a578, R.drawable.a577, R.drawable.a576, R.drawable.a575, R.drawable.a574, R.drawable.a573, R.drawable.a572, R.drawable.a571,
            R.drawable.a570, R.drawable.a569, R.drawable.a568, R.drawable.a567, R.drawable.a566, R.drawable.a565, R.drawable.a564, R.drawable.a563, R.drawable.a562, R.drawable.a561,
            R.drawable.a560, R.drawable.a559, R.drawable.a558, R.drawable.a557, R.drawable.a556, R.drawable.a555, R.drawable.a554, R.drawable.a553, R.drawable.a552, R.drawable.a551,
            R.drawable.a550, R.drawable.a549, R.drawable.a548, R.drawable.a547, R.drawable.a546, R.drawable.a545, R.drawable.a544, R.drawable.a543, R.drawable.a542, R.drawable.a541,
            R.drawable.a540, R.drawable.a539, R.drawable.a538, R.drawable.a537, R.drawable.a536, R.drawable.a535, R.drawable.a534, R.drawable.a533, R.drawable.a532, R.drawable.a531,
            R.drawable.a530, R.drawable.a529, R.drawable.a528, R.drawable.a527, R.drawable.a526, R.drawable.a525, R.drawable.a524, R.drawable.a523, R.drawable.a522, R.drawable.a521,
            R.drawable.a520, R.drawable.a519, R.drawable.a518, R.drawable.a517, R.drawable.a516, R.drawable.a515, R.drawable.a514, R.drawable.a513, R.drawable.a512, R.drawable.a511,
            R.drawable.a510, R.drawable.a509, R.drawable.a508, R.drawable.a507, R.drawable.a506, R.drawable.a505, R.drawable.a504, R.drawable.a503, R.drawable.a502, R.drawable.a501,

            R.drawable.a500, R.drawable.a499, R.drawable.a498, R.drawable.a497, R.drawable.a496, R.drawable.a495, R.drawable.a494, R.drawable.a493, R.drawable.a492, R.drawable.a491,
            R.drawable.a490, R.drawable.a489, R.drawable.a488, R.drawable.a487, R.drawable.a486, R.drawable.a485, R.drawable.a484, R.drawable.a483, R.drawable.a482, R.drawable.a481,
            R.drawable.a480, R.drawable.a479, R.drawable.a478, R.drawable.a477, R.drawable.a476, R.drawable.a475, R.drawable.a474, R.drawable.a473, R.drawable.a472, R.drawable.a471,
            R.drawable.a470, R.drawable.a469, R.drawable.a468, R.drawable.a467, R.drawable.a466, R.drawable.a465, R.drawable.a464, R.drawable.a463, R.drawable.a462, R.drawable.a461,
            R.drawable.a460, R.drawable.a459, R.drawable.a458, R.drawable.a457, R.drawable.a456, R.drawable.a455, R.drawable.a454, R.drawable.a453, R.drawable.a452, R.drawable.a451,
            R.drawable.a450, R.drawable.a449, R.drawable.a448, R.drawable.a447, R.drawable.a446, R.drawable.a445, R.drawable.a444, R.drawable.a443, R.drawable.a442, R.drawable.a441,
            R.drawable.a440, R.drawable.a439, R.drawable.a438, R.drawable.a437, R.drawable.a436, R.drawable.a435, R.drawable.a434, R.drawable.a433, R.drawable.a432, R.drawable.a431,
            R.drawable.a430, R.drawable.a429, R.drawable.a428, R.drawable.a427, R.drawable.a426, R.drawable.a425, R.drawable.a244, R.drawable.a423, R.drawable.a422, R.drawable.a421,
            R.drawable.a420, R.drawable.a419, R.drawable.a418, R.drawable.a417, R.drawable.a416, R.drawable.a415, R.drawable.a414, R.drawable.a413, R.drawable.a412, R.drawable.a411,
            R.drawable.a410, R.drawable.a409, R.drawable.a408, R.drawable.a407, R.drawable.a406, R.drawable.a405, R.drawable.a404, R.drawable.a403, R.drawable.a402, R.drawable.a401,

            R.drawable.a400, R.drawable.a399, R.drawable.a398, R.drawable.a397, R.drawable.a396, R.drawable.a395, R.drawable.a394, R.drawable.a393, R.drawable.a392, R.drawable.a391,
            R.drawable.a390, R.drawable.a389, R.drawable.a388, R.drawable.a387, R.drawable.a386, R.drawable.a385, R.drawable.a384, R.drawable.a383, R.drawable.a382, R.drawable.a381,
            R.drawable.a380, R.drawable.a379, R.drawable.a378, R.drawable.a377, R.drawable.a376, R.drawable.a375, R.drawable.a374, R.drawable.a373, R.drawable.a372, R.drawable.a371,
            R.drawable.a370, R.drawable.a369, R.drawable.a368, R.drawable.a367, R.drawable.a366, R.drawable.a365, R.drawable.a364, R.drawable.a363, R.drawable.a362, R.drawable.a361,
            R.drawable.a360, R.drawable.a359, R.drawable.a358, R.drawable.a357, R.drawable.a356, R.drawable.a355, R.drawable.a354, R.drawable.a353, R.drawable.a352, R.drawable.a351,
            R.drawable.a350, R.drawable.a349, R.drawable.a348, R.drawable.a347, R.drawable.a346, R.drawable.a345, R.drawable.a344, R.drawable.a343, R.drawable.a342, R.drawable.a341,
            R.drawable.a340, R.drawable.a339, R.drawable.a338, R.drawable.a337, R.drawable.a336, R.drawable.a335, R.drawable.a334, R.drawable.a333, R.drawable.a332, R.drawable.a331,
            R.drawable.a330, R.drawable.a329, R.drawable.a328, R.drawable.a327, R.drawable.a326, R.drawable.a325, R.drawable.a324, R.drawable.a323, R.drawable.a322, R.drawable.a321,
            R.drawable.a320, R.drawable.a319, R.drawable.a318, R.drawable.a317, R.drawable.a316, R.drawable.a315, R.drawable.a314, R.drawable.a313, R.drawable.a312, R.drawable.a311,
            R.drawable.a310, R.drawable.a309, R.drawable.a308, R.drawable.a307, R.drawable.a306, R.drawable.a305, R.drawable.a304, R.drawable.a303, R.drawable.a302, R.drawable.a301,

            R.drawable.a300, R.drawable.a299, R.drawable.a298, R.drawable.a297, R.drawable.a296, R.drawable.a295, R.drawable.a294, R.drawable.a293, R.drawable.a292, R.drawable.a291,
            R.drawable.a290, R.drawable.a289, R.drawable.a288, R.drawable.a287, R.drawable.a286, R.drawable.a285, R.drawable.a284, R.drawable.a283, R.drawable.a282, R.drawable.a281,
            R.drawable.a280, R.drawable.a279, R.drawable.a278, R.drawable.a277, R.drawable.a276, R.drawable.a275, R.drawable.a274, R.drawable.a273, R.drawable.a272, R.drawable.a271,
            R.drawable.a270, R.drawable.a269, R.drawable.a268, R.drawable.a267, R.drawable.a266, R.drawable.a265, R.drawable.a264, R.drawable.a263, R.drawable.a262, R.drawable.a261,
            R.drawable.a260, R.drawable.a259, R.drawable.a258, R.drawable.a257, R.drawable.a256, R.drawable.a255, R.drawable.a254, R.drawable.a253, R.drawable.a252, R.drawable.a251,
            R.drawable.a250, R.drawable.a249, R.drawable.a248, R.drawable.a247, R.drawable.a246, R.drawable.a245, R.drawable.a244, R.drawable.a243, R.drawable.a242, R.drawable.a241,
            R.drawable.a240, R.drawable.a239, R.drawable.a228, R.drawable.a237, R.drawable.a236, R.drawable.a235, R.drawable.a234, R.drawable.a233, R.drawable.a232, R.drawable.a231,
            R.drawable.a230, R.drawable.a229, R.drawable.a228, R.drawable.a227, R.drawable.a226, R.drawable.a225, R.drawable.a224, R.drawable.a223, R.drawable.a222, R.drawable.a221,
            R.drawable.a220, R.drawable.a219, R.drawable.a218, R.drawable.a217, R.drawable.a216, R.drawable.a215, R.drawable.a214, R.drawable.a213, R.drawable.a212, R.drawable.a211,
            R.drawable.a210, R.drawable.a209, R.drawable.a208, R.drawable.a207, R.drawable.a206, R.drawable.a205, R.drawable.a204, R.drawable.a203, R.drawable.a202, R.drawable.a201,

            R.drawable.a200, R.drawable.a199, R.drawable.a198, R.drawable.a197, R.drawable.a196, R.drawable.a195, R.drawable.a194, R.drawable.a193, R.drawable.a192, R.drawable.a191,
            R.drawable.a190, R.drawable.a189, R.drawable.a188, R.drawable.a187, R.drawable.a186, R.drawable.a185, R.drawable.a184, R.drawable.a183, R.drawable.a182, R.drawable.a181,
            R.drawable.a180, R.drawable.a179, R.drawable.a178, R.drawable.a177, R.drawable.a176, R.drawable.a175, R.drawable.a174, R.drawable.a173, R.drawable.a172, R.drawable.a171,
            R.drawable.a170, R.drawable.a169, R.drawable.a168, R.drawable.a167, R.drawable.a166, R.drawable.a165, R.drawable.a164, R.drawable.a163, R.drawable.a162, R.drawable.a161,
            R.drawable.a160, R.drawable.a158, R.drawable.a157, R.drawable.a156, R.drawable.a155, R.drawable.a154, R.drawable.a153, R.drawable.a152, R.drawable.a151,
            R.drawable.a150, R.drawable.a149, R.drawable.a148, R.drawable.a147, R.drawable.a146, R.drawable.a145, R.drawable.a144, R.drawable.a143, R.drawable.a142, R.drawable.a141,
            R.drawable.a140, R.drawable.a139, R.drawable.a138, R.drawable.a137, R.drawable.a136, R.drawable.a135, R.drawable.a134, R.drawable.a133, R.drawable.a132, R.drawable.a131,
            R.drawable.a130, R.drawable.a129, R.drawable.a128, R.drawable.a127, R.drawable.a126, R.drawable.a125, R.drawable.a124, R.drawable.a123, R.drawable.a122, R.drawable.a121,
            R.drawable.a120, R.drawable.a119, R.drawable.a118, R.drawable.a117, R.drawable.a116, R.drawable.a115, R.drawable.a114, R.drawable.a113, R.drawable.a112, R.drawable.a111,
            R.drawable.a110, R.drawable.a109, R.drawable.a108, R.drawable.a107, R.drawable.a106, R.drawable.a105, R.drawable.a104, R.drawable.a103, R.drawable.a102, R.drawable.a101,


            R.drawable.a100,R.drawable.a99, R.drawable.a98, R.drawable.a97, R.drawable.a96, R.drawable.a95, R.drawable.a94, R.drawable.a93, R.drawable.a92, R.drawable.a91,
            R.drawable.a90, R.drawable.a89, R.drawable.a88, R.drawable.a87, R.drawable.a86, R.drawable.a85, R.drawable.a84, R.drawable.a83, R.drawable.a82, R.drawable.a81,
            R.drawable.a80, R.drawable.a79, R.drawable.a78, R.drawable.a77, R.drawable.a76, R.drawable.a75, R.drawable.a74, R.drawable.a73, R.drawable.a72, R.drawable.a71,
            R.drawable.a70, R.drawable.a69, R.drawable.a68, R.drawable.a67, R.drawable.a66, R.drawable.a65, R.drawable.a64, R.drawable.a63, R.drawable.a62, R.drawable.a61,
            R.drawable.a60, R.drawable.a59, R.drawable.a58, R.drawable.a57, R.drawable.a56, R.drawable.a55, R.drawable.a54, R.drawable.a53, R.drawable.a52, R.drawable.a51,
            R.drawable.a50, R.drawable.a49, R.drawable.a48, R.drawable.a47, R.drawable.a46, R.drawable.a45, R.drawable.a44, R.drawable.a43, R.drawable.a42, R.drawable.a41,
            R.drawable.a40, R.drawable.a39, R.drawable.a38, R.drawable.a37, R.drawable.a36, R.drawable.a35, R.drawable.a34, R.drawable.a33, R.drawable.a32, R.drawable.a31,
            R.drawable.a30, R.drawable.a29, R.drawable.a28, R.drawable.a27, R.drawable.a26, R.drawable.a25, R.drawable.a24, R.drawable.a23, R.drawable.a22, R.drawable.a21,
            R.drawable.a20, R.drawable.a19, R.drawable.a18, R.drawable.a17, R.drawable.a16, R.drawable.a15, R.drawable.a14, R.drawable.a13, R.drawable.a12, R.drawable.a11,
            R.drawable.a10, R.drawable.a9,  R.drawable.a8,  R.drawable.a7,  R.drawable.a6,  R.drawable.a5,  R.drawable.a4,  R.drawable.a3,  R.drawable.a2,  R.drawable.a1


    };




    private Context ctx;
    private LayoutInflater layoutInflater;


    public CustomSwipeAdpter(Context ctx){
        this.ctx = ctx;
    }
    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view==(LinearLayout)o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView)item_view.findViewById(R.id.image_view);
        imageView.setImageResource(image_resources[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

     }

