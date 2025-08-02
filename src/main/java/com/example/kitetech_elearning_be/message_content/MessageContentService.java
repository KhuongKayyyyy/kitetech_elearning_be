package com.example.kitetech_elearning_be.message_content;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageContentService {
    private final MessageContentRepository messageContentRepository;
    private final MessageContentMapper messageContentMapper;

    public MessageContentDTO getLastMessage(final UUID messageRoomId) {
        return messageContentRepository.findTopByMessageRoomIdOrderByDateSentDesc(messageRoomId).map(
                m -> messageContentMapper.toDTO(m,new MessageContentDTO())).orElse(null);
    }
    public List<MessageContentDTO> getMessageByRoomId(final UUID roomId){
        return messageContentRepository.findByMessageRoomIdOrderByDateSentDesc(roomId)
                .stream()
                .map(m -> messageContentMapper.toDTO(m,new MessageContentDTO())).toList();
    }

    public MessageContentDTO save(final MessageContentDTO messageContentDTO) {
        final MessageContent messageContent = messageContentRepository.save(messageContentMapper.toEntity(messageContentDTO, new MessageContent()));
        return messageContentMapper.toDTO(messageContent, new MessageContentDTO());
    }

    public Long countUnseenMessage(final UUID roomId, final String username) {
        return messageContentRepository.countUnseenMessage(roomId, username);
    }
}
