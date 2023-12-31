package com.poll_app.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Set;

    @Entity
    public class Poll {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="POLL_ID")
        private Long id;

        @Column(name = "question_ID")

       @NotEmpty
        private String question;
        @OneToMany(cascade = {CascadeType.ALL})
        @JoinColumn(name="POLL_ID")
        @OrderBy
        @Size(min=2, max =6)
        private Set<Options> options; //= new java.util.LinkedHashSet<>();

        public Set<Options> getOptions() {
            return options;
        }

        public void setOptions(Set<Options> options) {
            this.options = options;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Poll{" +
                    "id=" + id +
                    ", question='" + question + '\'' +
                    ", options=" + options +
                    '}';
        }
    }

