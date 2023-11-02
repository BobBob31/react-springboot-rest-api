package toyproject.shop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shop.exception.CustomException;

import java.util.List;
import java.util.Optional;

import static toyproject.shop.exception.CustomErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

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
                            throw new CustomException(DUPLICATE_EMAIL_ERROR);
//                            IllegalArgumentException("이미 존재하는 회원입니다.");
                        });
    }

    public String signIn(UserDto userDto) {
        User findUser = userRepository.findByEmail(userDto.getEmail()).orElseThrow(
                () -> new CustomException(EMAIL_NOT_FOUND_ERROR)
//                IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
        if (!findUser.matchPassword(userDto.getPw())) {
            throw new CustomException(MISMATCH_PW_ERROR);
//            IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return userRepository.findByEmail(userDto.getEmail()).get().getName();
    }

    public void deleteUser(Long userId) {
        Optional<User> findUser = userRepository.findById(userId);
//        if (
        User user = findUser.orElseThrow(() -> new CustomException(EMAIL_NOT_FOUND_ERROR));
//                IllegalArgumentException("사용자를 찾을 수 없습니다."));// { // .isPresent()) {
//        User user = findUser.get();
        userRepository.delete(user);

        // } // else throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
    }
}
