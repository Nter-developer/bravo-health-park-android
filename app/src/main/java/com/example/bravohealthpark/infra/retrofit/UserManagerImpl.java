package com.example.bravohealthpark.infra.retrofit;

import static com.example.bravohealthpark.infra.preferences.SharedPreferenceBase.getSharedPreference;

import com.example.bravohealthpark.domain.user.UserEntity;
import com.example.bravohealthpark.domain.user.UserRepository;
import com.example.bravohealthpark.domain.user.service.UserManager;
import com.example.bravohealthpark.infra.preferences.APIPreferences;

public class UserManagerImpl implements UserManager {
    private final UserRepository userRepository;

    public UserManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUser() {
        String token = getSharedPreference(APIPreferences.SHARED_PREFERENCE_NAME_COOKIE);

        if (token != null && !token.isEmpty()) {
            try {
                return userRepository.find().get(); // 비동기 작업이 완료될 때까지 대기하고 결과를 반환
            } catch (Exception e) {
                // 예외 처리
            }
        }

        return null;
    }
}