/**
 * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja.servlet;

import com.google.inject.servlet.GuiceFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet filter that calls into a wrapped Guice filter which in turn will
 * call into Ninja to handle requests.  If a websocket handshake is detected
 * there is some logic to first delegate it to Ninja then hand if off to the
 * container for further processing.
 * 
 * @author jjlauer
 */
public class NinjaServletFilter implements Filter {

    private final GuiceFilter wrapped;
    
    public NinjaServletFilter() {
        this.wrapped = new GuiceFilter();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.wrapped.init(filterConfig);
    }
    
    @Override
    public void destroy() {
        this.wrapped.destroy();
    }

    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            // handle normally
            wrapped.doFilter(request, response, chain);
    }

}