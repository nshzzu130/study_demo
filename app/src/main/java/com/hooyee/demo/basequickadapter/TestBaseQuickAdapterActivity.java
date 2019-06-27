package com.hooyee.demo.basequickadapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hooyee.demo.R;
import com.hooyee.demo.base.BaseActivity;
import com.hooyee.demo.databinding.ActivityTestBaseQuickAdapterBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author hooyee
 * @desc 测试basequicklyadapter和@BindAdapterf注解
 * @date 2019/6/20
 **/
public class TestBaseQuickAdapterActivity extends BaseActivity<ActivityTestBaseQuickAdapterBinding> {
    public List<String> datas = new ArrayList();

    @Override
    protected void doDrawView() {
        addTestData();
        bindings.setDatas(datas);
        bindings.testRv.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, datas) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(android.R.id.text1, item);
            }
        });
        bindings.test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datas.add("test");
                bindings.setDatas(datas);
            }
        });
    }

    private void addTestData() {
        for (int i = 0; i < 10; i++) {
            datas.add(new Random(System.currentTimeMillis()).nextInt(10000) + "");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_base_quick_adapter;
    }


    /**
     * 这个属性可以自己随便定义，保持和xml一致就好，接收的参数也必须是对应的参数，泛型也要保持一致
    **/
    @BindingAdapter({"adapterData"})
    public static void autoRefresh(RecyclerView rv, List datas) {
        RecyclerView.Adapter adapter = rv.getAdapter();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
