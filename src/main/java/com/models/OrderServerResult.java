package com.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
public class OrderServerResult {
        Map<String, Object> data;

        @ServerTimestamp
        public Date orderDate;

        public OrderServerResult(Map<String, Object> data){
                this.data = data;
                this.orderDate = convertTimestampToDate((Timestamp)data.get("date"));
                this.data.remove("date");
        }

        protected Date convertTimestampToDate(Timestamp t){
                return t.toDate();
        }
}
