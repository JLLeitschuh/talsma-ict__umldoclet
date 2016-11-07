package nl.talsmasoftware.umldoclet.model;

import org.junit.Test;

import static nl.talsmasoftware.umldoclet.model.Reference.Side.from;
import static nl.talsmasoftware.umldoclet.model.Reference.Side.to;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Sjoerd Talsma
 */
public class ReferenceTest {

    @Test
    public void testSelfReference() {
        Reference ref = new Reference(from(getClass().getName()), "-->", to(getClass().getName()));
        assertThat(ref.isSelfReference(), is(true));
    }

    @Test
    public void testCanonical() {
        Reference ref1 = new Reference(from("type1"), "-->", to("type2", "*"));
        Reference ref2 = new Reference(from("type2", "*"), "<--", to("type1"));
        assertThat(ref1, is(equalTo(ref2)));
        assertThat(ref2, is(equalTo(ref1)));
        assertThat(ref1, hasToString("type1 --> * type2"));
        assertThat(ref2, hasToString("type2 * <-- type1"));
        assertThat(ref2.canonical(), hasToString("type1 --> * type2"));
    }

}
