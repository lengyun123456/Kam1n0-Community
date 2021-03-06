/*******************************************************************************
 * Copyright 2017 McGill University All rights reserved.
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
 *******************************************************************************/
package ca.mcgill.sis.dmas.kam1n0.problem.clone.features;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import ca.mcgill.sis.dmas.env.StringResources;
import ca.mcgill.sis.dmas.kam1n0.framework.disassembly.AsmLineNormalizationResource;
import ca.mcgill.sis.dmas.kam1n0.framework.disassembly.AsmLineNormalizer;
import ca.mcgill.sis.dmas.kam1n0.framework.disassembly.NormalizationSetting;
import ca.mcgill.sis.dmas.kam1n0.framework.disassembly.NormalizationSetting.NormalizationLevel;
import ca.mcgill.sis.dmas.kam1n0.utils.hash.SparseVector;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableMap.Builder;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class FeatureMneFreq extends Features {

	private static final long serialVersionUID = -5604699149842204077L;

	@XStreamOmitField
	List<String> features;

	public FeatureMneFreq() {

	}

	@Override
	public List<String> getFeatures(AsmLineNormalizer normalizer) {
		if (this.features == null)
			this.features = constructFeature(normalizer);
		return this.features;
	}

	@Override
	public void update(SparseVector vec, List<List<String>> blk, ImmutableMap<String, Integer> featMap) {
		for (List<String> tokens : blk) {
			Integer index = featMap.get(tokens.get(0));
			if (index != null)
				vec.inc(index);// score[index]++;
		}
	}

	public static ArrayList<String> constructFeature(AsmLineNormalizer normalizer) {
		return new ArrayList<>(normalizer.res.getALLOperations(normalizer.setting.normalizeOperation));
	}

}
