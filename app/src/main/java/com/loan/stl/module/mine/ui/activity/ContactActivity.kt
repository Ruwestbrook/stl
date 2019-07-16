package com.loan.stl.module.mine.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.common.RequestResultCode
import com.loan.stl.databinding.ActivityContactBinding
import com.loan.stl.module.mine.viewControl.ContactControl
import android.content.pm.PackageManager
import com.loan.stl.utils.ToastUtils
import android.provider.ContactsContract
import android.net.Uri
import com.alibaba.android.arouter.facade.annotation.Route
import com.loan.stl.router.RouterUrl


/**
author: russell
time: 2019-07-15:21:06
describe：
 */
@Route(path = RouterUrl.ACTIVITY_LINKER)
class ContactActivity:BaseActivity() {
    var isFirst = true
    private var contactControl: ContactControl? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactControl = ContactControl(this)
        val binding = DataBindingUtil.setContentView<ActivityContactBinding>(this, R.layout.activity_contact)
        binding.ctrl = contactControl
        setPageTitle("紧急联系人")
    }


    /*
    选择联系人回调
     */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null || data.data == null)
            return
        getPhoneContacts(data.data)
    }

    override fun onResume() {
        super.onResume()
        if (contactControl?.checkPermission() == true) {
            Thread(Runnable {
                if (isFirst) {
                    isFirst = false
                    contactControl?.subPhone()
                }
            }).start()
        }
    }

    /*
     权限检查
     */

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RequestResultCode.REQ_CONTACT_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    contactControl?.goChoose()
                } else {
                    ToastUtils.toast(R.string.linker_permission_error)

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }


    /**
     * 读取联系人信息
     * @param uri
     */
    private fun getPhoneContacts(uri: Uri?) {
        if(uri==null)
            return
        val contact = arrayOfNulls<String>(2)
        //得到ContentResolver对象
        val cr = contentResolver
        val cursor = cr.query(uri, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            //取得联系人姓名
            val nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            contact[0] = cursor.getString(nameFieldColumnIndex)
            contact[1] = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            contactControl?.contactVM?.phone=contact[1]
            cursor.close()
        }
    }

}