package fpoly.phongndtph56750.myapplication.activity.admin;

import android.os.Bundle;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;

import fpoly.phongndtph56750.myapplication.MyApplication;
import fpoly.phongndtph56750.myapplication.R;
import fpoly.phongndtph56750.myapplication.activity.BaseActivity;
import fpoly.phongndtph56750.myapplication.constant.ConstantKey;
import fpoly.phongndtph56750.myapplication.constant.GlobalFunction;
import fpoly.phongndtph56750.myapplication.databinding.ActivityAddCategoryBinding;
import fpoly.phongndtph56750.myapplication.model.Category;
import fpoly.phongndtph56750.myapplication.util.StringUtil;

public class AddCategoryActivity extends BaseActivity {

    private ActivityAddCategoryBinding mActivityAddCategoryBinding;
    private boolean isUpdate;
    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddCategoryBinding = ActivityAddCategoryBinding.inflate(getLayoutInflater());
        setContentView(mActivityAddCategoryBinding.getRoot());

        Bundle bundleReceived = getIntent().getExtras();
        if (bundleReceived != null) {
            isUpdate = true;
            mCategory = (Category) bundleReceived.get(ConstantKey.KEY_INTENT_CATEGORY_OBJECT);
        }

        initView();

        mActivityAddCategoryBinding.imgBack.setOnClickListener(v -> onBackPressed());
        mActivityAddCategoryBinding.btnAddOrEdit.setOnClickListener(v -> addOrEditCategory());
    }

    private void initView() {
        if (isUpdate) {
            mActivityAddCategoryBinding.tvTitle.setText(getString(R.string.edit_category_title));
            mActivityAddCategoryBinding.btnAddOrEdit.setText(getString(R.string.action_edit));
            mActivityAddCategoryBinding.edtName.setText(mCategory.getName());
            mActivityAddCategoryBinding.edtImage.setText(mCategory.getImage());
        } else {
            mActivityAddCategoryBinding.tvTitle.setText(getString(R.string.add_category_title));
            mActivityAddCategoryBinding.btnAddOrEdit.setText(getString(R.string.action_add));
        }
    }

    private void addOrEditCategory() {
        String strName = mActivityAddCategoryBinding.edtName.getText().toString().trim();
        String strImage = mActivityAddCategoryBinding.edtImage.getText().toString().trim();

        if (StringUtil.isEmpty(strName)) {
            Toast.makeText(this, getString(R.string.msg_name_category_require), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtil.isEmpty(strImage)) {
            Toast.makeText(this, getString(R.string.msg_image_category_require), Toast.LENGTH_SHORT).show();
            return;
        }

        // Update category
        if (isUpdate) {
            showProgressDialog(true);
            Map<String, Object> map = new HashMap<>();
            map.put("name", strName);
            map.put("image", strImage);

            MyApplication.get(this).getCategoryDatabaseReference()
                    .child(String.valueOf(mCategory.getId())).updateChildren(map, (error, ref) -> {
                showProgressDialog(false);
                Toast.makeText(AddCategoryActivity.this,
                        getString(R.string.msg_edit_category_successfully), Toast.LENGTH_SHORT).show();
                GlobalFunction.hideSoftKeyboard(AddCategoryActivity.this);
            });
            return;
        }

        // Add category
        showProgressDialog(true);
        long categoryId = System.currentTimeMillis();

        Category category = new Category(categoryId, strName, strImage);
        MyApplication.get(this).getCategoryDatabaseReference().child(String.valueOf(categoryId)).setValue(category, (error, ref) -> {
            showProgressDialog(false);
            mActivityAddCategoryBinding.edtName.setText("");
            mActivityAddCategoryBinding.edtImage.setText("");
            GlobalFunction.hideSoftKeyboard(this);
            Toast.makeText(this, getString(R.string.msg_add_category_successfully), Toast.LENGTH_SHORT).show();
        });
    }
}