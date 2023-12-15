package com.Marketing.MarketingAPI.models;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private NotifType type;
    private String content;
    private String sender;
}
