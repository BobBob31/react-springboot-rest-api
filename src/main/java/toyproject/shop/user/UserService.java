package toyproject.shop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Transactional
    public Long signUp(UserDto userDto) {
        validateDuplicateUser(userDto);
        User user = User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .pw(userDto.getPw())
                .build();
        return userRepository.save(user).getId();
    }

    public void validateDuplicateUser(UserDto userDto) {
        userRepository.findByEmail(userDto.getEmail())
                        .ifPresent(u -> {
                            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                        });
    }

    public Long signIn(UserDto userDto) {
        User findUser = userRepository.findByEmail(userDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
        if (!findUser.matchPassword(userDto.getPw())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return userRepository.findByEmail(userDto.getEmail()).get().getId();
    }

    public void deleteUser(Long userId) {
        Optional<User> findUser = userRepository.findById(userId);
//        if (
        User user = findUser.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));// { // .isPresent()) {
//        User user = findUser.get();
        userRepository.delete(user);

        // } // else throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
    }
}
