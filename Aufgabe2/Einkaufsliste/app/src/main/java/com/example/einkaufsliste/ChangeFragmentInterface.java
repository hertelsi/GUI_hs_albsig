package com.example.einkaufsliste;

import androidx.fragment.app.Fragment;

public interface ChangeFragmentInterface {
    void changeFragment(int id);
    void setUsernameText(String name);
    void setLoginItemText(String name);
    void setBuyingListName(String name);
}
