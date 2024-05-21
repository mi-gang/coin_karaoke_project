package com.oopsw.model.vo;

public class ReviewVO {
    private int reviewId;
    private String content;
    private float star;
    private int reservationId;

    
    public ReviewVO(int reviewId, String content, float star, int reservationId) {
        setReviewId(reviewId);
        setContent(content);
        setStar(star);
        setReservationId(reservationId);
    }
    
    public int getReviewId() {
        return reviewId;
    }
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public float getStar() {
        return star;
    }
    public void setStar(float star) {
        this.star = star;
    }
    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + reviewId;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + Float.floatToIntBits(star);
        result = prime * result + reservationId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReviewVO other = (ReviewVO) obj;
        if (reviewId != other.reviewId)
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (Float.floatToIntBits(star) != Float.floatToIntBits(other.star))
            return false;
        if (reservationId != other.reservationId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ReviewVO [reviewId=" + reviewId + ", content=" + content + ", star=" + star + ", reservationId="
                + reservationId + "]";
    }

    
}
