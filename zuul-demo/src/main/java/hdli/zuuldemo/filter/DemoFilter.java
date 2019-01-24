package hdli.zuuldemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 18:12 2019/1/24
 */
public class DemoFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		return null;
	}
}
