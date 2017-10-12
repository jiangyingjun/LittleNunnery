package com.shuai.model.bean.face;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jiangyingjun on 2017/10/12.
 */

public class Faces  {

    private String face_token;

//    private Attributes attributes;

    public Faces(){}


    public static class Attributes implements Parcelable{
        /**
         * 表情
         */
        private Emotion emotion;

        /**
         * 颜值
         */
        private Beauty beauty;
        /**
         *
         * 性别
         * */
        private Gender gender;

        /**
         *
         * 年龄
         * */

        private Age age;

        protected Attributes(Parcel in) {
            emotion = in.readParcelable(Emotion.class.getClassLoader());
            beauty = in.readParcelable(Beauty.class.getClassLoader());
            gender = in.readParcelable(Gender.class.getClassLoader());
            age = in.readParcelable(Age.class.getClassLoader());
        }

        public static final Creator<Attributes> CREATOR = new Creator<Attributes>() {
            @Override
            public Attributes createFromParcel(Parcel in) {
                return new Attributes(in);
            }

            @Override
            public Attributes[] newArray(int size) {
                return new Attributes[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(emotion, flags);
            dest.writeParcelable(beauty, flags);
            dest.writeParcelable(gender, flags);
            dest.writeParcelable(age, flags);
        }

        public static class Emotion implements Parcelable {

            private long anger;

            private long disgust;

            private long fear;

            private long happines;

            private long neutral;

            private long sadness;

            private long surprise;

            public Emotion() {
            }

            protected Emotion(Parcel in) {
                anger = in.readLong();
                disgust = in.readLong();
                fear = in.readLong();
                happines = in.readLong();
                neutral = in.readLong();
                sadness = in.readLong();
                surprise = in.readLong();
            }

            public static final Creator<Emotion> CREATOR = new Creator<Emotion>() {
                @Override
                public Emotion createFromParcel(Parcel in) {
                    return new Emotion(in);
                }

                @Override
                public Emotion[] newArray(int size) {
                    return new Emotion[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeLong(anger);
                dest.writeLong(disgust);
                dest.writeLong(fear);
                dest.writeLong(happines);
                dest.writeLong(neutral);
                dest.writeLong(sadness);
                dest.writeLong(surprise);
            }
        }


        public static class Beauty implements Parcelable {

            private long female_score;

            private long male_score;

            public Beauty() {
            }

            protected Beauty(Parcel in) {
                female_score = in.readLong();
                male_score = in.readLong();
            }

            public static final Creator<Beauty> CREATOR = new Creator<Beauty>() {
                @Override
                public Beauty createFromParcel(Parcel in) {
                    return new Beauty(in);
                }

                @Override
                public Beauty[] newArray(int size) {
                    return new Beauty[size];
                }
            };

            public long getFemale_score() {
                return female_score;
            }

            public void setFemale_score(long female_score) {
                this.female_score = female_score;
            }

            public long getMale_score() {
                return male_score;
            }

            public void setMale_score(long male_score) {
                this.male_score = male_score;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeLong(female_score);
                dest.writeLong(male_score);
            }
        }

        public static class Gender implements Parcelable {

            protected Gender(Parcel in) {
                value = in.readString();
            }

            public Gender() {
            }

            public static final Creator<Gender> CREATOR = new Creator<Gender>() {
                @Override
                public Gender createFromParcel(Parcel in) {
                    return new Gender(in);
                }

                @Override
                public Gender[] newArray(int size) {
                    return new Gender[size];
                }
            };

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            private String value;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(value);
            }
        }

        public static class Age implements Parcelable {

            protected Age(Parcel in) {
                value = in.readString();
            }

            public Age() {
            }

            public static final Creator<Age> CREATOR = new Creator<Age>() {
                @Override
                public Age createFromParcel(Parcel in) {
                    return new Age(in);
                }

                @Override
                public Age[] newArray(int size) {
                    return new Age[size];
                }
            };

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            private String value;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(value);
            }
        }


    }


    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

//    public Attributes getAttributes() {
//        return attributes;
//    }
//
//    public void setAttributes(Attributes attributes) {
//        this.attributes = attributes;
//    }
}

