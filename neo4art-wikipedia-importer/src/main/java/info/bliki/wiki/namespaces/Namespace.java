package info.bliki.wiki.namespaces;

import info.bliki.Messages;
import info.bliki.wiki.filter.Encoder;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import static info.bliki.wiki.namespaces.INamespace.NamespaceCode.*;
import static java.lang.String.CASE_INSENSITIVE_ORDER;

/**
 * Copied and modified from the <a href="https://bitbucket.org/axelclk/info.bliki.wiki/wiki/Home">info.bliki.wiki project</a>.
 *
 * @author Lorenzo Speranzoni
 * @since 4 Apr 2015
 */
public class Namespace implements INamespace {
    /**
     * Maps namespaces case-insensitively to their according
     * {@link NamespaceValue} objects.
     */
    protected final Map<String, NamespaceValue> TEXT_TO_NAMESPACE_MAP = new TreeMap<>(CASE_INSENSITIVE_ORDER);

    /**
     * Fast access to each {@link NamespaceValue} via an integer index similar
     * to its number code.
     *
     * @see Namespace#numberCodeToInt(int)
     * @see Namespace#intToNumberCode(int)
     */
    protected final NamespaceValue[] INT_TO_NAMESPACE = new NamespaceValue[35];

    /**
     * The &quot;Media&quot; namespace for the current language.
     */
    public final NamespaceValue MEDIA = new NamespaceValue(MEDIA_NAMESPACE_KEY, (NamespaceValue) null, "Media");
    /**
     * The &quot;Special&quot; namespace for the current language.
     */
    public final NamespaceValue SPECIAL = new NamespaceValue(SPECIAL_NAMESPACE_KEY, (NamespaceValue) null, "Special");
    /**
     * The &quot;Talk&quot; namespace for the current language.
     */
    public final NamespaceValue TALK = new NamespaceValue(TALK_NAMESPACE_KEY, "Talk");
    /**
     * The main namespace for the current language.
     */
    public final NamespaceValue MAIN = new NamespaceValue(MAIN_NAMESPACE_KEY, TALK, "");
    /**
     * The &quot;User talk&quot; namespace for the current language.
     */
    public final NamespaceValue USER_TALK = new NamespaceValue(USER_TALK_NAMESPACE_KEY, "User_talk");
    /**
     * The &quot;User&quot; namespace for the current language.
     */
    public final NamespaceValue USER = new NamespaceValue(USER_NAMESPACE_KEY, USER_TALK, "User");
    /**
     * The &quot;Meta talk&quot; namespace for the current language.
     */
    public final NamespaceValue PROJECT_TALK = new NamespaceValue(PROJECT_TALK_NAMESPACE_KEY, "Project_talk", "Meta_talk");
    /**
     * The &quot;Meta&quot; namespace for the current language.
     */
    public final NamespaceValue PROJECT = new NamespaceValue(PROJECT_NAMESPACE_KEY, PROJECT_TALK, "Project", "Meta");

    /**
     * NS_IMAGE and NS_IMAGE_TALK are the pre-v1.14 names for NS_FILE and
     * NS_FILE_TALK respectively, and are kept for compatibility.
     */
    public final NamespaceValue IMAGE_TALK = new NamespaceValue(FILE_TALK_NAMESPACE_KEY,  "File_talk", "Image_talk");
    public final NamespaceValue IMAGE = new NamespaceValue(FILE_NAMESPACE_KEY, IMAGE_TALK, "File", "Image");
    /**
     * The &quot;MediaWiki talk&quot; namespace for the current language.
     */
    public final NamespaceValue MEDIAWIKI_TALK = new NamespaceValue(MEDIAWIKI_TALK_NAMESPACE_KEY,  "MediaWiki_talk");
    /**
     * The &quot;MediaWiki&quot; namespace for the current language.
     */
    public final NamespaceValue MEDIAWIKI = new NamespaceValue(MEDIAWIKI_NAMESPACE_KEY, MEDIAWIKI_TALK, "MediaWiki");

    /**
     * The &quot;Module talk&quot; namespace for the current language.
     */
    public final NamespaceValue MODULE_TALK = new NamespaceValue(MODULE_TALK_NAMESPACE_KEY, "Module_talk");

    /**
     * The &quot;Module&quot; namespace for the current language.
     */
    public final NamespaceValue MODULE = new NamespaceValue(MODULE_NAMESPACE_KEY, MODULE_TALK, "Module");
    /**
     * The &quot;Template talk&quot; namespace for the current language.
     */
    public final NamespaceValue TEMPLATE_TALK = new NamespaceValue(TEMPLATE_TALK_NAMESPACE_KEY, "Template_talk");
    /**
     * The &quot;Template&quot; namespace for the current language.
     */
    public final NamespaceValue TEMPLATE = new NamespaceValue(TEMPLATE_NAMESPACE_KEY, TEMPLATE_TALK, "Template");

    /**
     * The &quot;Help talk&quot; namespace for the current language.
     */
    public final NamespaceValue HELP_TALK = new NamespaceValue(HELP_TALK_NAMESPACE_KEY,  "Help_talk");
    /**
     * The &quot;Help&quot; namespace for the current language.
     */
    public final NamespaceValue HELP = new NamespaceValue(HELP_NAMESPACE_KEY, HELP_TALK, "Help");
    /**
     * The &quot;Category talk&quot; namespace for the current language.
     */
    public final NamespaceValue CATEGORY_TALK = new NamespaceValue(CATEGORY_TALK_NAMESPACE_KEY, "Category_talk");
    /**
     * The &quot;Category&quot; namespace for the current language.
     */
    public final NamespaceValue CATEGORY = new NamespaceValue(CATEGORY_NAMESPACE_KEY, CATEGORY_TALK, "Category");
    /**
     * The &quot;Portal talk&quot; namespace for the current language.
     */
    public final NamespaceValue PORTAL_TALK = new NamespaceValue(PORTAL_TALK_NAMESPACE_KEY, "Portal_talk");
    /**
     * The &quot;Portal&quot; namespace for the current language.
     */
    public final NamespaceValue PORTAL = new NamespaceValue(PORTAL_NAMESPACE_KEY, PORTAL_TALK, "Portal");

    public final NamespaceValue BOOK = new NamespaceValue(BOOK_KEY, "Book");
    public final NamespaceValue BOOK_TALK = new NamespaceValue(BOOK_TALK_KEY, "Book_talk");
    public final NamespaceValue DRAFT = new NamespaceValue(DRAFT_KEY, "Draft");
    public final NamespaceValue DRAFT_TALK = new NamespaceValue(DRAFT_TALK_KEY, "Draft_talk");
    public final NamespaceValue EDUCATION_PROGRAM = new NamespaceValue(EDUCATION_PROGRAM_KEY, "Education_Program");
    public final NamespaceValue EDUCATION_PROGRAM_TALK = new NamespaceValue(EDUCATION_PROGRAM_TALK_KEY, "Education_Program_Talk");
    public final NamespaceValue TIMED_TEXT = new NamespaceValue(TIMED_TEXT_KEY, "TimedText");
    public final NamespaceValue TIMED_TEXT_TALK = new NamespaceValue(TIMED_TEXT_TALK_KEY, "TimedText_talk");
    public final NamespaceValue TOPIC = new NamespaceValue(TOPIC_KEY, "Topic");
    
    public final NamespaceValue GADGET = new NamespaceValue(GADGET_KEY, "Gadget");
    public final NamespaceValue GADGET_TALK = new NamespaceValue(GADGET_TALK_KEY, "Gadget_talk");
    public final NamespaceValue GADGET_DEFINITION = new NamespaceValue(GADGET_DEFINITION_KEY, "Gadget_definition");
    public final NamespaceValue GADGET_DEFINITION_TALK = new NamespaceValue(GADGET_DEFINITION_TALK_KEY, "Gadget_definition_talk");

    protected ResourceBundle fResourceBundle, fResourceBundleEn;

    public Namespace() {
        this((ResourceBundle) null);
    }

    public Namespace(Locale locale) {
        this(Messages.getResourceBundle(locale));
    }

    public Namespace(ResourceBundle resourceBundle) {
        fResourceBundle = resourceBundle;
        fResourceBundleEn = Messages.getResourceBundle(Locale.ENGLISH);
        initializeNamespaces();
    }

    @Override
    public boolean isNamespace(String namespace, NamespaceCode code) {
        NamespaceValue nsVal = getNamespace(namespace);
        if (nsVal != null) {
            return isNamespace(nsVal, code);
        }
        return false;
    }

    @Override
    public boolean isNamespace(INamespaceValue namespace, NamespaceCode code) {
        if (namespace == null) {
            return false;
        }
        return namespace.getCode() == code;
    }

    @Override
    public NamespaceValue getNamespace(String namespace) {
        return TEXT_TO_NAMESPACE_MAP.get(namespace);
    }

    @Override
    public NamespaceValue getNamespaceByNumber(NamespaceCode numberCode) {
        return getNamespaceByNumber(numberCode.code);
    }

    @Override
    public NamespaceValue getNamespaceByNumber(int numberCode) {
        final int arrayPos = numberCodeToInt(numberCode);
        if (arrayPos >= 0 && arrayPos < INT_TO_NAMESPACE.length) {
            return INT_TO_NAMESPACE[arrayPos];
        }
        return null;
    }

    /**
     * Converts an (external) namespace number code to the position in the
     * {@link #INT_TO_NAMESPACE} array.
     *
     * @param numberCode
     *            a code like {@link INamespace#MEDIA_NAMESPACE_KEY}
     *
     * @return an array index
     */
    protected static int numberCodeToInt(int numberCode) {
        if (numberCode >= -2 && numberCode <= 15) {
            return numberCode + 2;
        } else if (numberCode >= 100 && numberCode <= 101) {
            return numberCode - 100 + 18;
        } else if (numberCode >= 108 && numberCode <= 109) {
        	return numberCode - 108 + 20;
        } else if (numberCode >= 118 && numberCode <= 119) {
        	return numberCode - 118 + 22;
        } else if (numberCode >= 446 && numberCode <= 447) {
        	return numberCode - 446 + 24;
        } else if (numberCode >= 710 && numberCode <= 711) {
        	return numberCode - 710 + 26;
        } else if (numberCode >= 828 && numberCode <= 829) {
            return numberCode - 828 + 28;
        } else if (numberCode >= 2300 && numberCode <= 2303) {
        	return numberCode - 2303 + 30;
        } else if (numberCode >= 2600 && numberCode <= 2600) {
        	return numberCode - 2600 + 34;
        } else {
            throw new InvalidParameterException("unknown number code: "
                    + numberCode);
        }
    }

    /**
     * Converts an (internal) namespace number code (the position in the
     * {@link #INT_TO_NAMESPACE} array) to the external namespace number.
     *
     * @param numberCode
     *            internal array index
     *
     * @return a number code like {@link INamespace#MEDIA_NAMESPACE_KEY}
     */
    protected static int intToNumberCode(int numberCode) {
        if (numberCode >= 0 && numberCode <= 17) {
            return numberCode - 2;
        } else if (numberCode >= 18 && numberCode <= 19) {
            return numberCode + 100 - 18;
        } else if (numberCode >= 20 && numberCode <= 21) {
        	return numberCode - 108 - 20;
        } else if (numberCode >= 22 && numberCode <= 23) {
        	return numberCode - 118 - 22;
        } else if (numberCode >= 24 && numberCode <= 25) {
        	return numberCode - 446 - 24;
        } else if (numberCode >= 26 && numberCode <= 27) {
        	return numberCode - 710 - 26;
        } else if (numberCode >= 28 && numberCode <= 29) {
            return numberCode + 828 - 28;
        } else if (numberCode >= 30 && numberCode <= 33) {
        	return numberCode + 2303 - 30;
        } else if (numberCode >= 34 && numberCode <= 34) {
        	return numberCode + 2600 - 34;
        } else {
            throw new InvalidParameterException("unknown number code: "
                    + numberCode);
        }
    }

    @Override
    public ResourceBundle getResourceBundle() {
        return fResourceBundle;
    }

    protected enum ExtractType {
        REPLACE_TEXTS, APPEND_AS_ALIASES;
    }

    /**
     * Extracts the two namespace strings from the resource bundle into the
     * {@link #fNamespaces1} and {@link #fNamespaces2} arrays.
     *
     * @param ns1Id
     *            the first id in the bundle, e.g.
     *            {@link Messages#WIKI_API_MEDIA1}
     * @param ns2Id
     *            the first id in the bundle, e.g.
     *            {@link Messages#WIKI_API_MEDIA2}
     * @param code
     *            the namespace code
     */
    private void extractFromResource(ResourceBundle resourceBundle,
            String ns1Id, String ns2Id, NamespaceCode code, ExtractType cmd) {
        NamespaceValue namespace = getNamespaceByNumber(code);
        assert (namespace != null) : "undefined namespace code: " + code;
        String ns1 = Messages.getString(resourceBundle, ns1Id, null);
        if (ns1 != null) {
            String ns2 = Messages.getString(resourceBundle, ns2Id, null);
            switch (cmd) {
            case REPLACE_TEXTS:
                if (ns2 != null) {
                    namespace.setTexts(ns1, ns2);
                } else {
                    namespace.setTexts(ns1);
                }
            case APPEND_AS_ALIASES:
                namespace.addAlias(ns1);
                if (ns2 != null) {
                    namespace.addAlias(ns2);
                }
            }
        }
    }

    protected void extractFromResource(ResourceBundle resource, ExtractType cmd) {
        if (resource == null) {
            return;
        }
        extractFromResource(resource, Messages.WIKI_API_MEDIA1,         Messages.WIKI_API_MEDIA2, MEDIA_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_SPECIAL1,       Messages.WIKI_API_SPECIAL2, SPECIAL_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_TALK1,          Messages.WIKI_API_TALK2, TALK_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_USER1,          Messages.WIKI_API_USER2, USER_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_USERTALK1,      Messages.WIKI_API_USERTALK2, USER_TALK_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_META1,          Messages.WIKI_API_META2, PROJECT_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_METATALK1,      Messages.WIKI_API_METATALK2, PROJECT_TALK_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_IMAGE1,         Messages.WIKI_API_IMAGE2, FILE_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_IMAGETALK1,     Messages.WIKI_API_IMAGETALK2, FILE_TALK_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_MEDIAWIKI1,     Messages.WIKI_API_MEDIAWIKI2, MEDIAWIKI_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_MEDIAWIKITALK1, Messages.WIKI_API_MEDIAWIKITALK2, MEDIAWIKI_TALK_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_TEMPLATE1,      Messages.WIKI_API_TEMPLATE2, TEMPLATE_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_TEMPLATETALK1,  Messages.WIKI_API_TEMPLATETALK2, TEMPLATE_TALK_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_HELP1,          Messages.WIKI_API_HELP2, HELP_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_HELPTALK1,      Messages.WIKI_API_HELPTALK2, HELP_TALK_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_CATEGORY1,      Messages.WIKI_API_CATEGORY2, CATEGORY_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_CATEGORYTALK1,  Messages.WIKI_API_CATEGORYTALK2, CATEGORY_TALK_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_PORTAL1,        Messages.WIKI_API_PORTAL2, PORTAL_NAMESPACE_KEY, cmd);
        extractFromResource(resource, Messages.WIKI_API_PORTALTALK1,    Messages.WIKI_API_PORTALTALK2, PORTAL_TALK_NAMESPACE_KEY, cmd);
    }

    private void initializeNamespaces() {
        extractFromResource(fResourceBundle, ExtractType.REPLACE_TEXTS);
        extractFromResource(fResourceBundleEn, ExtractType.APPEND_AS_ALIASES);

        // Aliases as defined by
        // https://en.wikipedia.org/wiki/Wikipedia:Namespace#Aliases
        PROJECT.addAlias("WP");
        PROJECT.addAlias("Project");
        PROJECT_TALK.addAlias("WT");
        PROJECT_TALK.addAlias("Project_talk");
        // already in the English resource bundle:
        // IMAGE.addAlias("Image");
        // IMAGE_TALK.addAlias("Image_talk");
    }

    @Override
    public NamespaceValue getTalkspace(String namespace) {
        NamespaceValue nsVal = getNamespace(namespace);
        if (nsVal != null) {
            return nsVal.getTalkspace();
        }
        return null;
    }

    @Override
    public NamespaceValue getContentspace(String talkNamespace) {
        NamespaceValue nsVal = getNamespace(talkNamespace);
        if (nsVal != null) {
            return nsVal.getContentspace();
        }
        return null;
    }

    @Override
    public INamespaceValue getMedia() {
        return MEDIA;
    }

    @Override
    public INamespaceValue getSpecial() {
        return SPECIAL;
    }

    @Override
    public INamespaceValue getMain() {
        return MAIN;
    }

    @Override
    public INamespaceValue getTalk() {
        return TALK;
    }

    @Override
    public INamespaceValue getUser() {
        return USER;
    }

    @Override
    public INamespaceValue getUser_talk() {
        return USER_TALK;
    }

    @Override
    public INamespaceValue getProject() { return PROJECT; }

    @Override
    public INamespaceValue getProject_talk() {
        return PROJECT_TALK;
    }

    @Override
    public INamespaceValue getImage() {
        return IMAGE;
    }

    @Override
    public INamespaceValue getImage_talk() {
        return IMAGE_TALK;
    }

    @Override
    public INamespaceValue getMediaWiki() {
        return MEDIAWIKI;
    }

    @Override
    public INamespaceValue getMediaWiki_talk() {
        return MEDIAWIKI_TALK;
    }

    @Override
    public INamespaceValue getModule() {
        return MODULE;
    }

    @Override
    public INamespaceValue getTemplate() {
        return TEMPLATE;
    }

    @Override
    public INamespaceValue getTemplate_talk() {
        return TEMPLATE_TALK;
    }

    @Override
    public INamespaceValue getHelp() {
        return HELP;
    }

    @Override
    public INamespaceValue getHelp_talk() {
        return HELP_TALK;
    }

    @Override
    public INamespaceValue getCategory() {
        return CATEGORY;
    }

    @Override
    public INamespaceValue getCategory_talk() {
        return CATEGORY_TALK;
    }

    @Override
    public INamespaceValue getPortal() {
        return PORTAL;
    }

    @Override
    public INamespaceValue getPortal_talk() {
        return PORTAL_TALK;
    }

    @Override
    public String[] splitNsTitle(String fullTitle) {
        return splitNsTitle(fullTitle, true, ' ', true);
    }

    @Override
    public String[] splitNsTitle(String fullTitle,
            boolean underScoreIsWhitespace, char whiteSpaceChar,
            boolean firstCharacterAsUpperCase) {
        int colonIndex = fullTitle.indexOf(':');
        if (colonIndex != (-1)) {
            String maybeNs = Encoder.normaliseTitle(
                    fullTitle.substring(0, colonIndex), underScoreIsWhitespace,
                    whiteSpaceChar, firstCharacterAsUpperCase);
            if (getNamespace(maybeNs) != null) {
                // this is a real namespace
                return new String[] {
                        maybeNs,
                        Encoder.normaliseTitle(
                                fullTitle.substring(colonIndex + 1),
                                underScoreIsWhitespace, whiteSpaceChar,
                                firstCharacterAsUpperCase) };
            }
            // else: page belongs to the main namespace and only contains a
            // colon
        }
        return new String[] {
                "",
                Encoder.normaliseTitle(fullTitle, underScoreIsWhitespace,
                        whiteSpaceChar, firstCharacterAsUpperCase) };
    }

    /**
     * Base class for all namespace constants.
     *
     * @author Nico Kruber, kruber@zib.de
     */
    public class NamespaceValue implements INamespaceValue {
        private final NamespaceCode code;
        private final String[] canonicalAliases;
        private List<String> texts;
        private NamespaceValue talkspace;
        private NamespaceValue contentspace;

        /**
         * Constructor for talk namespaces.
         *
         * @param code
         *            the (internal) integer code of this namespace
         * @param isTalkspace
         *            must be <tt>true</tt> (needed to distinguish this
         *            constructor from the other in case of <tt>null</tt> talk
         *            spaces)
         * @param aliases
         *            all aliases identifying this namespace
         */
        private NamespaceValue(NamespaceCode code, String... aliases) {
            this.code = code;
            this.texts = new ArrayList<>(2);
            int arrayPos = numberCodeToInt(code.code);
            assert (INT_TO_NAMESPACE[arrayPos] == null);
            INT_TO_NAMESPACE[arrayPos] = this;
            // contentspace is set by the content NamespaceValue
            this.talkspace = this;
            this.canonicalAliases = aliases;
            setTexts(aliases);
        }

        /**
         * Constructor for content namespaces.
         *
         * @param code the (internal) integer code of this namespace
         * @param talkspace the associated talk namespace (must not be <tt>null</tt>)
         * @param aliases all aliases identifying this namespace
         */
        private NamespaceValue(NamespaceCode code, NamespaceValue talkspace, String... aliases) {
            this(code, aliases);
            // mapping of talkspace to content space is 1:1 if a talkspace exists
            if (talkspace != null) {
                assert (talkspace.contentspace == null);
                this.talkspace = talkspace;
                this.talkspace.contentspace = this;
            }
            this.contentspace = this;
        }

        @Override
        public NamespaceCode getCode() {
            return code;
        }

        @Override
        public void setTexts(String... aliases) {
            assert (aliases.length >= 1);
            // remove old texts:
            for (String text : this.texts) {
                TEXT_TO_NAMESPACE_MAP.remove(text);
                TEXT_TO_NAMESPACE_MAP.remove(text.replace(' ', '_'));
                TEXT_TO_NAMESPACE_MAP.remove(text.replace('_', ' '));
            }
            // note: don't assign the fixed-size list of Arrays.asList to texts!
            texts = new ArrayList<>(aliases.length);
            for (String alias : aliases) {
                assert (alias != null);
                addAlias(alias);
            }
        }

        @Override
        public void addAlias(String alias) {
            if (!TEXT_TO_NAMESPACE_MAP.containsKey(alias)) {
                texts.add(alias);
                TEXT_TO_NAMESPACE_MAP.put(alias, this);
                TEXT_TO_NAMESPACE_MAP.put(alias.replace(' ', '_'), this);
                TEXT_TO_NAMESPACE_MAP.put(alias.replace('_', ' '), this);
            }
        }

        @Override
        public String getPrimaryText() {
            return texts.get(0);
        }

        @Override
        public String getCanonicalName() {
            return this.canonicalAliases[0];
        }

        @Override
        public List<String> getTexts() {
            return texts;
        }

        @Override
        public NamespaceValue getTalkspace() {
            return talkspace;
        }

        @Override
        public NamespaceValue getContentspace() {
            return contentspace;
        }

        @Override
        public NamespaceValue getAssociatedspace() {
            if (isSubject()) {
                return getTalkspace();
            } else if (isTalk()) {
                return getContentspace();
            } else {
                return null;
            }
        }

        @Override
        public String makeFullPagename(String pageName) {
            String primaryText = getPrimaryText();
            if (primaryText.isEmpty()) {
                return pageName;
            } else {
                return primaryText + ":" + pageName;
            }
        }

        @Override
        public boolean isType(NamespaceCode code) {
            return this.code == code;
        }

        @Override
        public boolean hasSubpages() {
            return this.code.hasSubpages();
        }

        @Override
        public boolean hasGenderDistinction() {
            return this.code == USER_NAMESPACE_KEY || this.code == USER_TALK_NAMESPACE_KEY;
        }

        @Override
        public boolean isCapitalized() {
            return true;
        }

        @Override
        public boolean isContent() {
            return this.code == MAIN_NAMESPACE_KEY;
        }

        @Override
        public boolean isIncludable() {
            return true;
        }

        @Override
        public boolean isMovable() {
            return false;
        }

        @Override
        public boolean isSubject() {
            return this.talkspace != this;
        }

        @Override
        public boolean isTalk() {
            return this.talkspace == this;
        }

        @Override
        public String toString() {
            return getPrimaryText();
        }
    }
}
