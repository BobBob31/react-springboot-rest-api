package toyproject.shop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long signUp(UserDto userDto) {
        validateDuplicateUser(userDto);
        User user = User.builder()
                .userEmail(userDto.getUserEmail())
                .userName(userDto.getUserName())
                .userPw(userDto.getUserPw())
                .build();
        return userRepository.save(user).getUserId();
    }

    public void validateDuplicateUser(UserDto userDto) {
        userRepository.findByUserEmail(userDto.getUserEmail())
                        .ifPresent(u -> {
                            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                        });
    }

    public Long signIn(UserDto userDto) {
        User findUser = userRepository.findByUserEmail(userDto.getUserEmail()).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
        if (!findUser.matchPassword(userDto.getUserPw())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return userRepository.findByUserEmail(userDto.getUserEmail()).get().getUserId();
    }

    public void deleteUser(Long userId) {
        Optional<User> findUser = userRepository.findById(userId);
        if (findUser.isPresent()) {
            User user = findUser.get();
            userRepository.delete(user);

        } else throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
    }
}
