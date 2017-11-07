package com.example.missj.goaduch.com.example.missj.fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuAdapter;
import android.view.ContextMenu;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.missj.goaduch.R;
import com.example.missj.goaduch.com.example.missj.Adapter.AdaperUser;
import com.example.missj.goaduch.com.example.missj.business.BusinessUser;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuItem;
import com.example.missj.goaduch.com.example.missj.control.SliderMenuView;
import com.example.missj.goaduch.com.example.missj.model.ModelUser;

import static com.example.missj.goaduch.com.example.missj.database.RegexTools.IsChineseEnglishNum;

/**
 * Created by miss.j on 2017/10/28.
 */

public class UserBodyFragment extends FrameBase implements SliderMenuView.OnSlideMenuListenner {
    View v;
    private ListView  mUserList;
    private AdaperUser mAdaperUser;
    private BusinessUser mBusinessUser;
    private  ModelUser mModelUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_user, container, false);

        InitVarible();
       InitView();
       InitListeners();
       BindData();
       CreateSlideMenu(R.array.SlideMenuUser);
        return  v;
    }

    public  void InitView()
    {
        mUserList = (ListView) v.findViewById(R.id.lvUserList);

    }
    public  void InitVarible()
    {
     //   mAdaperUser = new AdaperUser(getContext());
        mBusinessUser = new BusinessUser(getActivity());

    }
    public  void BindData()
    {
        mAdaperUser = new AdaperUser(getContext());
        mUserList.setAdapter(mAdaperUser);

    }
    public  void InitListeners()
    {
            registerForContextMenu(mUserList);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo _AdapterContextMenuInfo =(AdapterView.AdapterContextMenuInfo) menuInfo;
        AdaperUser _AdapterUser = (AdaperUser) mUserList.getAdapter();
          mModelUser =(ModelUser) _AdapterUser.getItem(_AdapterContextMenuInfo.position);
        menu.setHeaderIcon(R.mipmap.user_small_icon);
        menu.setHeaderTitle(mModelUser.getUserName());

        createMenu(menu); //

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                //  OnAddOrEditUserListener(mModelUser, );
                showUseraddOrEditDialog( mModelUser);
                break;
            case 2:
                 Delete();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }


    @Override
    public void onSlidMenuItemClick(View pView, SliderMenuItem pSlideMenuItemClick) {
        Toast.makeText(getActivity(), pSlideMenuItemClick.getTitle(),Toast.LENGTH_SHORT).show();
        ShowUserAddOrEditDialog(0);
        showUseraddOrEditDialog(null);

    }
    private void Delete()
    {
        String _Message = getString(R.string.DialogMessageUserDelete, new Object[]{mModelUser.getUserName()});

        ShowAlertDialog(R.string.DialogTitleDelete, _Message, new OnDeleteClickListener());
    }

    private class   OnDeleteClickListener implements DialogInterface.OnClickListener
    {


        public void onClick(DialogInterface dialog, int which)
        {
            boolean _Result = mBusinessUser.HideUserByUserID(mModelUser.getUserId());

            if(_Result)
                BindData();

        }



    }
    private  void showUseraddOrEditDialog(ModelUser pModelUser)
    {
        View _View = getLayouInflater().inflate(R.layout.user_add_or_edit,null);
        EditText _EditText = _View.findViewById(R.id.etUserName);

        if(pModelUser != null)
        {
                _EditText.setText(pModelUser.getUserName());

        }
        _EditText.requestFocus();
        String _Title;
        if(pModelUser == null)
        {
            _Title = getString(R.string.DialogTitleUser,new Object[]{ getString(R.string.TitleAdd)});

        }
        else {
            _Title = getString(R.string.DialogTitleUser,new Object[]{ getString(R.string.TitleEdit)});

        }
        AlertDialog.Builder _Builder =  new AlertDialog.Builder(getActivity());
        _Builder.setTitle(_Title)
                .setView(_View)
                .setIcon(R.mipmap.user_small_icon)
                .setNeutralButton(R.string.ButtonSave,new OnAddOrEditUserListener(pModelUser,_EditText, true))
                .setNegativeButton(R.string.ButtonCancle,new OnAddOrEditUserListener(null, null, false))
                .show();

    }
    private  class  OnAddOrEditUserListener implements DialogInterface.OnClickListener {
        private ModelUser _ModelUser;
        private EditText etUserName;
        private Boolean mIsSaveButton;

        public OnAddOrEditUserListener(ModelUser _ModelUser, EditText etUserName, Boolean isSaveButton) {
            this._ModelUser = _ModelUser;
            this.etUserName = etUserName;
            mIsSaveButton = isSaveButton;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (mIsSaveButton == false) {
                SetAlerDialogIsClose(dialogInterface, true);
                return;
            }
            if (_ModelUser == null) {
                _ModelUser = new ModelUser();
            }
            String _String = etUserName.getText().toString().trim();
            if ((IsChineseEnglishNum(_String) == false)|| (_String == "")) {
                Toast.makeText(getActivity(), getString(R.string.CheckDataTextChineseEnglishNum,_String), Toast.LENGTH_SHORT).show();
                SetAlerDialogIsClose(dialogInterface, false);
            } else {
                SetAlerDialogIsClose(dialogInterface, true);

            if (mBusinessUser.IsExistUserByUserName(_String, _ModelUser.getUserId())) { //把当前用户排除掉再查存在不存在同名用户
                Toast.makeText(getActivity(), R.string.CheckDataTextUserExist, Toast.LENGTH_SHORT).show();
                SetAlerDialogIsClose(dialogInterface, false);
            }else
            {   SetAlerDialogIsClose(dialogInterface, true);
                boolean result;
                _ModelUser.setUserName(etUserName.getText().toString().trim());
                if(_ModelUser.getUserId() == 0)
                {
                    result = mBusinessUser.insertUser(_ModelUser);
                }else
                {
                    result = mBusinessUser.updateUserByUserID(_ModelUser);
                }
                if(result)
                    BindData();
                else
                {
                    Toast.makeText(getActivity(), R.string.TipsAddFail, Toast.LENGTH_SHORT).show();
                }
            }
            }
        }
    }
}
