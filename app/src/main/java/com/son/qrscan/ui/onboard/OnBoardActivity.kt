import android.view.LayoutInflater
import com.son.common.ui.base.BaseActivity
import com.son.qrscan.databinding.ActivityOnboardBinding



class OnBoardActivity : BaseActivity<ActivityOnboardBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityOnboardBinding {
        return ActivityOnboardBinding.inflate(inflater)
    }

}