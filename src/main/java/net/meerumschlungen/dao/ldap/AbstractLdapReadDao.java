package net.meerumschlungen.dao.ldap;

import javax.naming.ldap.LdapContext;

public abstract class AbstractLdapReadDao<T> implements LdapReadDao<T> {
    private LdapContext ctx;
    public AbstractLdapReadDao(LdapContext ctx) {
        this.ctx = ctx;
    }
    @Override
    public LdapContext getLdapContext() {
        return this.ctx;
    }
}
