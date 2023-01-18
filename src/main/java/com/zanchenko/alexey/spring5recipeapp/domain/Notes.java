package com.zanchenko.alexey.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    private Recipe recipe; // we don't need to specify a
    //cascade because this instance we are going to allow the Recipe to own this.
    //We don't want if we delete the Notes object, we don't want to go back and delete the Recipe object.
    //So we don't want cascade, operations happening but the inverse, if we want to delete a Recipe, of course
    //we want to delete the recipeNotes.
    @Lob
    private String recipeNotes;

}


//  So just just to recap what's happening there,
//we're seeing effectively a circular reference get created because of the
//bi-directional relationship and I'm excluding out notes in category so we
//can exclude out recipe. So it doesn't switch back on itself in that hashcode
//function and cause the the stack overflow here.

// Итак, просто чтобы подытожить, что там происходит, мы видим,
// что фактически циклическая ссылка создается из-за двунаправленные
// отношения, и я исключаю notes из category, поэтому мы можно
// исключить из рецепта. Таким образом, он не переключается обратно
// в этом хэш-коде функцию и вызвать здесь переполнение стека.